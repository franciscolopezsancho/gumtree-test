package com.gumtree.services;

import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import com.gumtree.services.ContactsFilter;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: fran
 * Date: 09/05/2015
 */
public class ContactsFilterTest {


    Contact a = new Contact("a", Gender.FEMALE, new DateTime(2004, 12, 25, 12, 0, 0, 0));
    Contact b = new Contact("b", Gender.MALE, new DateTime(2004, 12, 26, 12, 0, 0, 0));
    Contact c = new Contact("c", Gender.FEMALE, new DateTime(2005, 1, 26, 12, 0, 0, 0));
    Contact d = new Contact("d", Gender.MALE, new DateTime(2005, 2, 26, 12, 0, 0, 0));

    List<Contact> contacts = new ArrayList<Contact>();

    @Before
    public void setUp(){
        contacts.add(a);
        contacts.add(b);
        contacts.add(c);
        contacts.add(d);
    }



    @Test
    public void shouldFilterByGender(){
        //GIVEN a list with 2 males and 2 females -- see contacts list

        //WHEN
        List<Contact> filteredList = ContactsFilter.filterByGender(Gender.MALE,contacts);
        //THEN  should find 2 males
        assertTrue(filteredList.contains(b));
        assertTrue(filteredList.contains(d));
        assertTrue(!filteredList.contains(a));
        assertTrue(!filteredList.contains(c));

    }
    @Test
    public void shouldOderDescByBirthDate() {
        //GIVEN a list with ascending birthDate a,b,c,d

        //WHEN  sort asc false
        ContactsFilter.sortByBirthDate(false,contacts);
        //THEN d should be first and b last
        assertEquals("d",contacts.get(0).getName());
        assertEquals("c",contacts.get(1).getName());
        assertEquals("b",contacts.get(2).getName());
        assertEquals("a",contacts.get(3).getName());
    }

    @Test
    public void shouldOderAscByBirthDate() {
        //GIVEN a list with ascending birthDate a,b,c,d

        //WHEN  sort asc false
        ContactsFilter.sortByBirthDate(true,contacts);
        //THEN d should be first and b last
        assertEquals("a",contacts.get(0).getName());
        assertEquals("b",contacts.get(1).getName());
        assertEquals("c",contacts.get(2).getName());
        assertEquals("d",contacts.get(3).getName());
    }

    @Test
    public void shouldFindByName() {
        //GIVEN a list with ascending birthDate a,b,c,d

        //WHEN  sort by name
        //THEN d should be first and b last
        assertEquals(a,ContactsFilter.filterByName("a", contacts).get(0));
        assertEquals(b,ContactsFilter.filterByName("b", contacts).get(0));
        assertEquals(c,ContactsFilter.filterByName("c", contacts).get(0));
        assertEquals(d,ContactsFilter.filterByName("d", contacts).get(0));
    }

    @Test
    public void shouldFindBirthDiff(){
        assertEquals(-1, TimeUnit.MILLISECONDS.toDays(ContactsFilter.findBirthDateDiff(a,b)));
    }


}
