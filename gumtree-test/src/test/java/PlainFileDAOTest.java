import com.gumtree.AddressBookDAO;
import com.gumtree.PlainFileDAO;
import com.gumtree.PlainFileDAOFactory;
import com.gumtree.bo.AddressBookAccessException;
import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * User: fran
 * Date: 09/05/2015
 */
public class PlainFileDAOTest {

    @Test
    public void shouldReturnContacts() throws AddressBookAccessException {
        AddressBookDAO abD = PlainFileDAOFactory.getInstance().getAddressBookDAO()   ;
        //first name  on file
        //GIVEN a file
//        Bill McKnight, Male, 16/03/77
//        Paul Robinson, Male, 15/01/85
//        Gemma Lane, Female, 20/11/91
//        Sarah Stone, Female, 20/09/80
//        Wes Jackson, Male, 14/08/74
        DateTimeFormatter formatter = DateTimeFormat.forPattern(Contact.DATE_FORMAT);
        assertTrue(abD.getByGender("").contains(new Contact("Bill McKnight", Gender.MALE, formatter.parseDateTime("16/03/77"))))  ;

    }

    @Test
    public void shouldReturnBeginAndEnd() throws AddressBookAccessException {
        PlainFileDAO abD = new PlainFileDAO()   ;
        //first name  on file
        assertEquals(0,abD.getContent().indexOf("Bill McKnight"))  ;

        //last date on file
        assertTrue(abD.getContent().indexOf("14/08/74") != -1)  ;

        //last date on file
        assertEquals(abD.getContent().indexOf("14/08/74") + 8, abD.getContent().length())  ;
    }


}
