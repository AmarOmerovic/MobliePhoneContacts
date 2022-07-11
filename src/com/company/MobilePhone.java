package com.company;

import java.util.ArrayList;
import java.util.Locale;

public class MobilePhone {
    private static ArrayList<Contacts> myContacts;

    public MobilePhone() {
        myContacts = new ArrayList<>();
    }

    //VALIDATION & ADDING NEW CONTACT
    private boolean isEmpty(String string){
        return string.isEmpty();
    }

    public boolean addContact(String name, String phoneNumber){
        if (isEmpty(name) || isEmpty(phoneNumber)){
            return false;
        }else {
            myContacts.add(new Contacts(name.toUpperCase(Locale.ROOT), phoneNumber));
            return true;
        }
    }



    //VALIDATION & PRINTING CONTACTS
    private boolean contactSizeValidation() {
        return myContacts.size() != 0;
    }

    public int contactSize(){
        if (contactSizeValidation()){
            return myContacts.size();
        }else
            return -1;
    }

    public String printContactName(int index){
        if (contactSizeValidation()){
            return myContacts.get(index).getName();
        }else {
            return null;
        }
    }

    public String printContactPhoneNumber(int index){
        if (contactSizeValidation()){
            return myContacts.get(index).getPhoneNumber();
        }else {
            return null;
        }
    }



    //FINDING INDEXES
    private int index(String name){
        for(int i = 0; i < myContacts.size(); i++){
            if (myContacts.get(i).getName().equals(name.toUpperCase(Locale.ROOT))){
                return i;
            }
        }
        return -1;
    }



    //CHECKING IF CONTACT IS CREATED
    public boolean doesExist(String name){
        if (index(name) >= 0){
            return myContacts.get(index(name)).getName().equals(name.toUpperCase(Locale.ROOT));
        }else {
            return false;
        }
    }



    //UPDATING CONTACTS
    public boolean updateContact(String oldName, String newName, String newPhoneNumber){
        if (doesExist(oldName) && (!newName.isEmpty()) && (!newPhoneNumber.isEmpty()) && ((index(oldName)) >= 0)){
            myContacts.set(index(oldName), new Contacts(newName.toUpperCase(Locale.ROOT), newPhoneNumber));
            return true;
        }else {
            return false;
        }
    }

    public boolean updateContact(String oldName, String string, int userInput){
        if (doesExist(oldName) && (!string.isEmpty()) && ((index(oldName)) >= 0)){
            if (userInput == 2){
                myContacts.set(index(oldName), new Contacts(string.toUpperCase(Locale.ROOT), myContacts.get(index(oldName)).getPhoneNumber()));
                return true;
            }else if (userInput == 3){
                myContacts.set(index(oldName), new Contacts(myContacts.get(index(oldName)).getName(), string.toUpperCase(Locale.ROOT)));
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }



    //REMOVING CONTACT
    public boolean removeContact(String name){
        if (doesExist(name) && index(name) >= 0){
            myContacts.remove(index(name));
            return true;
        }else {
            return false;
        }
    }



    //SEARCH FOR CONTACT VALIDATION
    public boolean searchForContact(String name){
        return doesExist(name) && contactSizeValidation() && index(name) >= 0;
    }




}
