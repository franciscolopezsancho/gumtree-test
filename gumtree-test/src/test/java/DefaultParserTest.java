import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import com.gumtree.bridge.DefaultParser;
import com.gumtree.bridge.GenericParser;
import com.gumtree.bridge.MapperNGB;
import com.gumtree.exceptions.AddressBookAccessException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * User: fran
 * Date: 09/05/2015
 */
public class DefaultParserTest {

    String path2File = "/Users/fran/Interviews/Gumtree/gumtree-test/src/main/resources/address-book.txt";

    @Test
    public void shouldReturnBeginAndEnd() throws AddressBookAccessException {
        DefaultParser abD = new DefaultParser()   ;
        //first name  on file
        assertEquals(0,abD.getContent(path2File).indexOf("Bill McKnight"))  ;

        //last date on file
        assertTrue(abD.getContent(path2File).indexOf("14/08/74") != -1)  ;

        //last date on file
        assertEquals(abD.getContent(path2File).indexOf("14/08/74") + 8, abD.getContent(path2File).length())  ;
    }

    @Test
    public void shouldReturnContacts() throws AddressBookAccessException {
        GenericParser abD = new DefaultParser()   ;
        //first name  on file
        //GIVEN a file
        //        Bill McKnight, Male, 16/03/77
        //        Paul Robinson, Male, 15/01/85
        //        Gemma Lane, Female, 20/11/91
        //        Sarah Stone, Female, 20/09/80
        //        Wes Jackson, Male, 14/08/74
        DateTimeFormatter formatter = DateTimeFormat.forPattern(MapperNGB.DATE_FORMAT);
        assertTrue(abD.getContacts(abD.getContent(path2File)).contains(new Contact("Bill McKnight", Gender.MALE, formatter.parseDateTime("16/03/77"))))  ;
    }




}
