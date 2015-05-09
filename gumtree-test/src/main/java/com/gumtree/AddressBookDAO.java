package com.gumtree;

import com.gumtree.bo.AddressBookAccessException;
import com.gumtree.bo.Contact;

import java.util.List;

/**
 * User: fran
 * Date: 09/05/2015
 */
public interface AddressBookDAO {
     //TODO add connections abstraction layer?

    public List<Contact> getByGender(String gender) throws AddressBookAccessException ;


}
