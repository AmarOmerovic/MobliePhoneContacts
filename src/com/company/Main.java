package com.company;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone();

    public static void main(String[] args) {
        boolean quit = false;
        options();
        while (!quit){
            System.out.print("\t- Enter your choice: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (userInput){
                case 0:
                    System.out.println("\tQuitting...");
                    quit = true;
                    break;
                case 1:
                    options();
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    addNewContact();
                    break;
                case 4:
                    updateExistingContact();
                    break;
                case 5:
                    removeContact();
                    break;
                case 6:
                    searchContact();
                    break;
                default:
                    System.out.println("\t* Wrong input, try again! *");
                     break;
            }
        }
    }

    public static void options(){
        System.out.println("\t\t*** MANAGE CONTACTS ***");
        System.out.println("\t0. Quit.");
        System.out.println("\t1. Show contact options.");
        System.out.println("\t2. Show contact list.");
        System.out.println("\t3. Add new contact.");
        System.out.println("\t4. Update existing contact.");
        System.out.println("\t5. Remove contact.");
        System.out.println("\t6. Search for a specific contact.");
        System.out.println();
    }

    public static void displayContacts(){
        if (mobilePhone.contactSize() >= 0) {
            System.out.println("\t\t* ALL CONTACTS *");
                if (print()){
                    System.out.println();
                    System.out.println("\t* To go back to the main menu press \"1\"! *");
                }
        }else {
            System.out.println("\t* No contacts created! *");
            System.out.println();
            options();
        }
    }

    public static boolean print(){
        if (mobilePhone.contactSize() >= 0) {
            for (int i = 0; i < mobilePhone.contactSize(); ++i) {
                if (mobilePhone.printContactName(i) != null && mobilePhone.printContactPhoneNumber(i) != null)
                System.out.println((i + 1) + ")");
                System.out.println("\tName: " + mobilePhone.printContactName(i) + "\n\tPhone number: " + mobilePhone.printContactPhoneNumber(i));
            }
            return true;
        }
        return false;
    }

    public static void addNewContact() {

        boolean quit = false;

        while (!quit) {
            System.out.println("\t\t* ADD CONTACT *");
            System.out.println("\t1. Add new contact.");
            System.out.println("\t2. Back.");
            System.out.println();
            System.out.print("\t- Enter your choice: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1) {
                System.out.println();
                System.out.println("\tAdding new contact... ");
                System.out.println("\t* Please fill the needed information's bellow! *");
                System.out.print("\t- Name: ");
                String name = scanner.nextLine();
                System.out.print("\t- Phone number: ");
                String phoneNumber = scanner.nextLine();
                if (mobilePhone.addContact(name, phoneNumber)) {
                    System.out.println();
                    System.out.println("\t* New contact added successfully! *");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t* There was a problem! *");
                    System.out.println("\t* New contact was not created, please make sure to enter all information's correctly. *");
                    System.out.println();
                }
            } else if (userInput == 2) {
                System.out.println();
                System.out.println("\t* Returning to the main menu! *");
                System.out.println();
                quit = true;
                options();
            } else {
                System.out.println();
                System.out.println("\t* Wrong input, try again! *");
                System.out.println();
            }
        }
    }

    public static void updateExistingContact(){
        boolean quit = false;

        while (!quit) {
            System.out.println("\t\t* UPDATE CONTACTS *");
            System.out.println("\t1. Update contact name & phone number.");
            System.out.println("\t2. Update contact name.");
            System.out.println("\t3. Update contact phone number.");
            System.out.println("\t4. Back.");
            System.out.println();
            System.out.print("\t- Enter your choice: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 1: case 2: case 3:
                    updateNameAndOrPhoneNumber(userInput);
                    break;
                case 4:
                    System.out.println();
                    System.out.println("\t* Returning to the main menu! *");
                    System.out.println();
                    quit = true;
                    options();
                    break;
                default:
                    System.out.println();
                    System.out.println("\t* Wrong input, try again! *");
                    System.out.println();
                    break;
            }
        }
    }

    public static void updateNameAndOrPhoneNumber(int userInput) {
        String newContactName = "";
        String newPhoneNumber = "";

        if (userInput > 0 && userInput < 4) {
            System.out.println();
            if (userInput == 1) {
                System.out.println("\tUpdating contact name & phone number...");
            } else if (userInput == 2) {
                System.out.println("\tUpdating contact name...");
            } else if (userInput == 3) {
                System.out.println("\tUpdating contact phone number...");
            }
            System.out.println("\t* Please fill the needed information's bellow! *");
            System.out.print("\t- Enter the name of the contact you want to update: ");
            String oldContactName = scanner.nextLine();

            if (mobilePhone.doesExist(oldContactName)) {
                System.out.println("\tUpdating...");
                if (userInput == 1){
                    System.out.print("\t- Enter the new name: ");
                    newContactName = scanner.nextLine();
                    System.out.print("\t- Enter the new phone number: ");
                    newPhoneNumber = scanner.nextLine();
                    System.out.println();
                }else if (userInput == 2){
                    System.out.print("\t- Enter the new name: ");
                    newContactName = scanner.nextLine();
                }else if (userInput == 3){
                    System.out.print("\t- Enter the new phone number: ");
                    newPhoneNumber = scanner.nextLine();
                }

                if (mobilePhone.updateContact(oldContactName, newContactName, newPhoneNumber) || mobilePhone.updateContact(oldContactName, newContactName, userInput) || mobilePhone.updateContact(oldContactName, newPhoneNumber, userInput)) {
                    System.out.println();
                    System.out.println("\t* Contact updated successfully! *");
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("\t* There was a problem on updating your contact, please try again later! *");
                    System.out.println();
                }
            } else {
                System.out.println();
                System.out.println("\t* Contact with that name does not exist in your contacts! *");
                System.out.println();
            }
        }
    }

    public static void removeContact(){
        boolean quit = false;

        while (!quit) {
            System.out.println("\t\t* REMOVE CONTACT *");
            System.out.println("\t1. Remove contact.");
            System.out.println("\t2. Back.");
            System.out.println();
            System.out.print("\t- Enter your choice: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1) {
                System.out.println();
                System.out.println("\tRemoving contact...");
                System.out.println("\t* Please fill the needed information's bellow! *");
                System.out.print("\t- Enter the name of the contact you want to remove: ");
                String contactName = scanner.nextLine();
                System.out.println("\tRemoving...");
                if (mobilePhone.removeContact(contactName)) {
                        System.out.println();
                        System.out.println("\t* Contact removed successfully! *");
                        System.out.println();
                }else {
                    System.out.println();
                    System.out.println("\t* There was a problem! *");
                    System.out.println("\t* Contact was not removed, please make sure that the contact you want to remove exists! *");
                    System.out.println();
                }
            } else if (userInput == 2) {
                System.out.println();
                System.out.println("\t* Returning to the main menu! *");
                System.out.println();
                quit = true;
                options();
            } else {
                System.out.println();
                System.out.println("\t* Wrong input, try again! *");
                System.out.println();
            }
        }
    }

    public static void searchContact(){
        boolean quit = false;

        while (!quit) {
            System.out.println("\t\t* SEARCH FOR CONTACT *");
            System.out.println("\t1. Search for a specific contact.");
            System.out.println("\t2. Back.");
            System.out.println();
            System.out.print("\t- Enter your choice: ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput == 1) {
                System.out.println();
                System.out.println("\tSearching contact...");
                System.out.println("\t* Please fill the needed information's bellow! *");
                System.out.print("\t- Enter the name of the contact you are searching for: ");
                String contactName = scanner.nextLine();
                System.out.println("\tSearching...");
                if (mobilePhone.searchForContact(contactName)) {
                    System.out.println();
                    System.out.println("\t* Contact found! *");
                    print();
                    System.out.println();
                }else {
                    System.out.println();
                    System.out.println("\t* There was a problem! *");
                    System.out.println("\t* Contact was not found, please make sure that the contact you want to find exists! *");
                    System.out.println();
                }
            } else if (userInput == 2) {
                System.out.println();
                System.out.println("\t* Returning to the main menu! *");
                System.out.println();
                quit = true;
                options();
            } else {
                System.out.println();
                System.out.println("\t* Wrong input, try again! *");
                System.out.println();
            }
        }
    }


}
