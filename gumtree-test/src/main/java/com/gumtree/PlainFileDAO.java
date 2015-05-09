package com.gumtree;

import com.gumtree.bo.AddressBookAccessException;
import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
public class PlainFileDAO implements AddressBookDAO {



    public List<Contact> getByGender(String gender) throws AddressBookAccessException {
         String[] lines = getContent().toString().split("\n");
        List<Contact> contacts = new ArrayList();
        for(String line : lines){
            //TODO handle malformed txt
            try{
                String[] fields = line.split(",");
                DateTimeFormatter formatter = DateTimeFormat.forPattern(Contact.DATE_FORMAT);
                contacts.add(new Contact(fields[0].trim(),toGenderEnum(fields[1].trim()),formatter.parseDateTime(fields[2].trim())));
            } catch (Exception e){
                System.out.print(e);
            }
        }
        return contacts;}


    public Gender toGenderEnum(String gender) {
        Gender genderEnum = null;
        if(gender.toLowerCase().equals("male")){genderEnum = Gender.MALE;
        }else if(gender.toLowerCase().equals("female")){
            genderEnum =  Gender.FEMALE ; }
        return genderEnum;


    }

    public StringBuilder getContent() throws AddressBookAccessException {
       try {
           String filePath = "/Users/fran/Interviews/Gumtree/gumtree-test/src/main/resources/address-book.txt";
           int start = 0;
           StringBuilder content = new StringBuilder();
           Path path = Paths.get(filePath);
           AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
           ByteBuffer buf = ByteBuffer.allocate(10);
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
                    throw new AddressBookAccessException();
            }
        }

}
