package com.gumtree.bridge;

import com.gumtree.exceptions.GumtreeAccessFileException;
import com.gumtree.bo.Contact;
import com.gumtree.exceptions.GumtreeParseException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: fran
 * Date: 09/05/2015
 */
public abstract class GenericParser {

    public final IMapper mapper;
    public final String splitter;
    final static Logger logger = Logger.getLogger(GenericParser.class);


    public GenericParser(){
        //default implementation
        this.mapper = new MapperNGB();
        this.splitter = ",";
    }

    /**
     * Bridge pattern to be used here passing
     * whatever splitter or mapper suit best
     * @param splitter
     * @param mapper
     */
    public GenericParser(String splitter, IMapper mapper){
        this.mapper = mapper;
        this.splitter = splitter;
    }

    /**
     * Transforms every line of the file
     * into a List of Contacts
     * @param content
     * @return
     * @throws com.gumtree.exceptions.GumtreeAccessFileException
     */
    public List<Contact> getContacts(StringBuilder content) {
        String[] lines = content.toString().split("\n");
        List<Contact> contacts = new ArrayList();
        for(String line : lines){
            try{
                String[] fields = line.split(splitter);
                contacts.add(mapper.map(fields));
            } catch (GumtreeParseException e){
                //TODO plenty of room to improve exceptions
                logger.info("line couldn't be formatted..skipping this line. Its content was: "+line);
            }
        }
        return contacts;
    }

    /**
     * Get content of file from sPath
     * @param sPath
     * @return
     * @throws com.gumtree.exceptions.GumtreeAccessFileException
     */
    public StringBuilder getContent(String sPath) throws GumtreeAccessFileException {
        try {
            int start = 0;
            StringBuilder content = new StringBuilder();
            Path path = Paths.get(sPath);
            AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
            ByteBuffer buf = ByteBuffer.allocate(100);
            Future result = ch.read(buf, start);
            int length;
            while ((length = (Integer) result.get()) != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    content.append((char) buf.get());
                }
                buf.clear();
                start += length;

                result = ch.read(buf, start);
            }
            ch.close();
            return content;
        }catch ( IOException | ExecutionException | InterruptedException e){
            throw new GumtreeAccessFileException(e);
        }
    }



}
