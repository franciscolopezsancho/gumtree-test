import com.gumtree.AddressBookDAO;
import com.gumtree.PlainFileDAOFactory;
import com.gumtree.bo.AddressBookAccessException;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * User: fran
 * Date: 09/05/2015
 */
public class PlainFileDAOTest {

    @Test
    public void shouldReturnBeginAndEnd() throws AddressBookAccessException {
        AddressBookDAO abD = PlainFileDAOFactory.getInstance().getAddressBookDAO()   ;
        //first name  on file
        assertEquals(abD.select().indexOf("Bill"),0)  ;

        //last date on file
        assertTrue(abD.select().indexOf("14/08/74") != -1)  ;

        //last date on file
        assertEquals(abD.select().indexOf("14/08/74")+8,abD.select().length())  ;
    }


}
