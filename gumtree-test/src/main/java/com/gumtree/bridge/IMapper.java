package com.gumtree.bridge;

import com.gumtree.bo.Contact;
import com.gumtree.exceptions.GumtreeParseException;

/**
 * User: fran
 * Date: 10/05/2015
 */
public interface IMapper {

   public Contact map(String[] fields) throws GumtreeParseException;
}
