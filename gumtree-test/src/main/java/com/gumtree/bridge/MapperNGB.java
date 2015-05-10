package com.gumtree.bridge;

import com.gumtree.bo.Contact;
import com.gumtree.bo.Gender;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Mapper for name gender birthDate
 * User: fran
 * Date: 10/05/2015
 */
public class MapperNGB implements IMapper {

    public static final String DATE_FORMAT = "dd/MM/yy" ;


    public Contact map(String[] fields){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
        return new Contact(fields[0].trim(),toGenderEnum(fields[1].trim()),formatter.parseDateTime(fields[2].trim()));
    }


    public Gender toGenderEnum(String gender) {
        Gender genderEnum = null;
        if(gender.toLowerCase().equals("male")){genderEnum = Gender.MALE;
        }else if(gender.toLowerCase().equals("female")){
            genderEnum =  Gender.FEMALE ; }
        return genderEnum;


    }

}
