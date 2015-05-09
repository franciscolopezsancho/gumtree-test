package com.gumtree.bo;

import org.joda.time.DateTime;

/**
 * User: fran
 * Date: 09/05/2015
 */
public class Contact {

    private String name;
    private Gender gender;
    private DateTime birthDate;

    public static final String DATE_FORMAT = "dd/MM/yy" ;

    public Contact(String name,Gender gender,DateTime birthDate){
        setBirthDate(birthDate);
        setGender(gender);
        setName(name);
    }



    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public  synchronized Gender getGender() {
        return gender;
    }

    public  synchronized void setGender(Gender gender) {
        this.gender = gender;
    }

    public  synchronized DateTime getBirthDate() {
        return birthDate;
    }

    public  synchronized void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (birthDate != null ? !birthDate.equals(contact.birthDate) : contact.birthDate != null) return false;
        if (gender != contact.gender) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }
}


