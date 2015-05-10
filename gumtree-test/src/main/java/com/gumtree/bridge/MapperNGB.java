package com.gumtree.bridge;

import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import com.gumtree.exceptions.GumtreeParseException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Mapper for name gender birthDate
 * User: fran
 * Date: 10/05/2015
 */
public class MapperNGB implements IMapper {

    public static final String DATE_FORMAT = "dd/MM/yy" ;


    public Contact map(String[] fields) throws GumtreeParseException {
        Contact parsedContact;
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
            parsedContact = new Contact(fields[0].trim(), toGenderEnum(fields[1].trim()), formatter.parseDateTime(fields[2].trim()));
        }catch(Exception e){
            throw new GumtreeParseException(e);
        }
        return parsedContact;
    }


    public Gender toGenderEnum(String gender) {
        Gender genderEnum = null;
        if(gender.toLowerCase().equals("male")){genderEnum = Gender.MALE;
        }else if(gender.toLowerCase().equals("female")){
            genderEnum =  Gender.FEMALE ; }
        return genderEnum;


    }

}
