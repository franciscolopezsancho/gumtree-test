package com.gumtree;

import com.gumtree.bo.AddressBookAccessException;
import com.gumtree.bo.Contact;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: fran
 * Date: 09/05/2015
 */
public class PlainFileDAO implements AddressBookDAO {



    public List<Contact> getByGender(String gender) throws AddressBookAccessException {
        try {
            String filePath = "readfile.txt";
            Path path = Paths.get(filePath);
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(100);
            Future result = channel.read(buffer, 0);
            while (!result.isDone()) {


                System.out.println("Do something else while reading is in progress... ");

            }
            System.out.println("Reading done: " + result.isDone());
            System.out.println("Reading done: " + result.get());
        }catch ( IOException | ExecutionException | InterruptedException e){

        }
        return null;}



    public StringBuilder select() throws AddressBookAccessException {
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
               while (!result.isDone()) {
                   System.out.println("You can use this thread for another task");
                   Thread.sleep(100);
               }
               buf.flip();
           while (buf.hasRemaining()) {
                content.append((char) buf.get());
            }
               buf.clear();
               result = ch.read(buf, start);
               start += length;
           }
           ch.close();
           System.out.println(content);
           return content;


       }catch ( IOException | ExecutionException | InterruptedException e){
                    throw new AddressBookAccessException();
            }
        }

}
