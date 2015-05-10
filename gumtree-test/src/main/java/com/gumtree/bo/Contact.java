package com.gumtree.bo;

import org.joda.time.DateTime;

import java.util.Comparator;

/**
 * User: fran
 * Date: 09/05/2015
 */
public class Contact implements Comparable<Contact> {

    private String name;
    private Gender gender;
    private DateTime birthDate;


    public Contact(){
    }

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

    public int compareTo(Contact compareContact) {

        long compareQuantity = ((Contact) compareContact).getBirthDate().getMillis();

        //ascending order
        return safeLongToInt(this.getBirthDate().getMillis() - compareQuantity);
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public static Comparator<Contact> COMPARE_DATES_ASC
            = new Comparator<Contact>() {

        public int compare(Contact c1, Contact c2) {

            DateTime birthDate1 = c1.getBirthDate();
            DateTime birthDate2 = c2.getBirthDate();

            //ascending order
            return birthDate1.compareTo(birthDate2);

        }

    };

    public static Comparator<Contact> COMPARE_DATES_DESC
            = new Comparator<Contact>() {

        public int compare(Contact c1, Contact c2) {

            DateTime birthDate1 = c1.getBirthDate();
            DateTime birthDate2 = c2.getBirthDate();

            //descending order
            return birthDate2.compareTo(birthDate1);
        }

    };

}


