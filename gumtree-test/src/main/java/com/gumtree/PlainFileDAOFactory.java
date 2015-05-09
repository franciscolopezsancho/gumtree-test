package com.gumtree;

/**
 * User: fran
 * Date: 09/05/2015
 */
public class PlainFileDAOFactory implements DAOFactory {

    private static DAOFactory instance = null;

    protected PlainFileDAOFactory(){};

    public static DAOFactory getInstance(){
        if(instance == null){
            instance = new PlainFileDAOFactory();
        }
        return instance;
    }

    private AddressBookDAO addBooDAO;

    public  AddressBookDAO getAddressBookDAO(){
         if(addBooDAO == null){
             addBooDAO = new PlainFileDAO();
         }
        return addBooDAO;
    }
}
