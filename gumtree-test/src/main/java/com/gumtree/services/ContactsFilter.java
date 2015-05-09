package com.gumtree.services;

import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;

import java.util.ArrayList;
import java.util.List;

/**
 * User: fran
 * Date: 09/05/2015
 */
public class ContactsFilter {


    public List<Contact> filterByGender(Gender gender,List<Contact> contacts){
        List<Contact> afterFilter = new ArrayList<>();
        for(Contact contact : contacts){
            if(contact.getGender().equals(gender)){
                afterFilter.add(contact);
            }
        }
        return afterFilter;
    }

    public void sortByBirthDate(boolean asc,List<Contact> contacts) {
        if (asc) {
            contacts.sort(Contact.COMPARE_DATES_ASC);
        } else {
            contacts.sort(Contact.COMPARE_DATES_DESC);
        }
    }

    public List<Contact> filterByName(String name,List<Contact> contacts){
        List<Contact> afterFilter = new ArrayList<>();
        for(Contact contact : contacts){
            if(contact.getName().contains(name)){
                afterFilter.add(contact);
            }
        }
        return afterFilter;
    }

}
