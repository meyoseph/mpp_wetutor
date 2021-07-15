package com.example.WeTutor.functional;

import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class FunctionalMain {

    public static void main(String[] args) throws InterruptedException, IOException {
        char choice = 'z';
        Scanner keyboard = new Scanner(System.in);

        System.out.println("****************************************************************************************************");
        System.out.println("******************************** Welcome to WeTutor Easy Console ***********************************");
        System.out.println("******************** Play with the console to get better search results ****************************");
        // Connect to our mock database to have access and get all our data.
        Database.connect();
        // Console menu
        while (true){
            // ------MENU-------
            System.out.println("Please, choose one of the following functions to execute: ");
            System.out.println(
                    "\ta) Given a list of users, identify parents and get only active or parents with subscriptions");
            System.out.println(
                    "\tb) Given a list of users, identify tutors and get the top 5 tutors with ratings more than 4");
            System.out.println(
                    "\tc) Given a list of users, identify tutors and get tutors younger than 25, specialized in English, and who can speak Turkish");
            System.out.println(
                    "\tx) Exit");

            // ---WAIT-INPUT---
            System.out.println("Your choice:");
            choice = keyboard.next().toLowerCase().charAt(0);

            // ---SEARCHING---
            List<User> users = Database.getUsers();;
            List<Profile> profiles;

            switch (choice) {
                case 'a':
                    System.out.println("******************************************************************************************************************************");
                    System.out.println("************************************ Looking for active parents or parents with subscriptions ... ****************************");
                    System.out.println();
                    // Expected output: [ parentTwo@gmail.com, parentFour@gmail.com, parentSix@gmail.com, parentEight@gmail.com ]
                    System.out.println("************ Search result   :  "+TutorUtilFunctions.getActiveParents.apply(users)+"  *****");
                    System.out.println();
                    System.out.println("******************************************************************************************************************************");
                    System.out.println("******************************************************************************************************************************");
                    break;
                case 'b':
                    System.out.println("***********************************************************************************************************************************");
                    System.out.println("************************************ Looking for the top 5 tutors with ratings more than 4 ... ************************************");
                    System.out.println();
                    // Expected output: [TutorEightFirst TutorEightLast, TutorOneFirst TutorOneLast, TutorSevenFirst TutorSevenLast]
                    System.out.println("************ Search result   :  "+TutorUtilFunctions.findTopKTutors.apply(users, 4, 5L)+" ******");
                    System.out.println();
                    System.out.println("***********************************************************************************************************************************");
                    System.out.println("***********************************************************************************************************************************");
                    break;
                case 'c':
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("************************************ Looking for tutors younger than 25, specialized in Information Technology, and who can speak English ... ****************");
                    System.out.println("");
                    // Expected output: [ TutorThreeFirst TutorThreeLast ]
                    System.out.println("**************************************************** Search result   :   "
                            +TutorUtilFunctions.tutorWithMajorSubjectAndLanguage.apply(users,25, "Information Technology", "English")
                            +" ****************************************************");
                    System.out.println("");
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("**************************************************************************************************************************************************************");
                    break;
                // ADD MORE FUNCTIONS HERE
                case 'x':
                    System.out.println("***********************************************************************************");
                    System.out.println("***************Thank you for visiting, we hope to see you again. \uD83D\uDE00 \uD83D\uDE00 *************");
                    System.out.println("***********************************************************************************");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Enter a key to continue...");
            keyboard.next();
        }
    }
}
