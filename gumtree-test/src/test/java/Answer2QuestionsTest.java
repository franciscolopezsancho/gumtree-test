import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import com.gumtree.bridge.DefaultParser;
import com.gumtree.exceptions.GumtreeAccessFileException;
import com.gumtree.services.ContactsFilter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * User: fran
 * Date: 10/05/2015
 */
public class Answer2QuestionsTest {


    String path2File = "src/main/resources/address-book.txt";
    DefaultParser abD;
    List<Contact> allContacts;

    @Before
    public void setUp()  throws GumtreeAccessFileException {
        abD = new DefaultParser()   ;
        allContacts = abD.getContacts(abD.getContent(path2File));
        //GIVEN this is the content of the file
        //        Bill McKnight, Male, 16/03/77
        //        Paul Robinson, Male, 15/01/85
        //        Gemma Lane, Female, 20/11/91
        //        Sarah Stone, Female, 20/09/80
        //        Wes Jackson, Male, 14/08/74

    }


    @Test
    public void howManyMales(){
        assertEquals(3,ContactsFilter.filterByGender(Gender.MALE,allContacts).size());
    }

    @Test
    public void whoIsTheOldest(){
        ContactsFilter.sortByBirthDate(true, allContacts);
        assertEquals("Wes Jackson",allContacts.get(0).getName());
    }

    @Test
    public void howManyDaysOlderIsBillThanPaul(){
        //from www.timeanddate.com the diff is 2862
        //with end date not included in the calculation

        //knowing that is just one I can get the first result in both cases
        Contact Paul = ContactsFilter.filterByName("Paul Robinson",allContacts).get(0);
        Contact Bill = ContactsFilter.filterByName("Bill McKnight",allContacts).get(0);

        assertEquals(2862, TimeUnit.MILLISECONDS.toDays(ContactsFilter.findBirthDateDiff(Paul,Bill)));

    }

}
