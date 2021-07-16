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
                    "\td) Given a list of users, identify tutors and get total approved profiles of tutors");
            System.out.println(
                    "\te) Given a list of users, identify tutors and get top 4 tutors having 'Bachelor' and 3 years of experience");
            System.out.println(
                    "\tf) Given a list of users, identify tutors and get top Female young tutors");
            System.out.println(
                    "\tg) Given a list of users, identify parents and get total number subscribed parents");
            System.out.println(
                    "\th) Given a list of users, identify tutors and get top tutors with 7 years of experience in 'Mathematics;");
            System.out.println(
                    "\ti) Given a list of users, identify tutors and get top tutors gives tutor on 'LocationOne' city");
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
                case 'd':
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("************************************ Looking for Total Number of Approved Profiles of Tutors ... ****************");
                    System.out.println("");
                    // Expected output: [ 5 ]
                    System.out.println("**************************************************** Search result   :   "
                            +TutorUtilFunctions.getTotalApprovedTutors.apply(users)
                            +" ****************************************************");
                    System.out.println("");
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("**************************************************************************************************************************************************************");
                    break;

                case 'e':
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("************************************ Looking for top tutors having Bachelor and  3 years of experience... ****************");
                    System.out.println("");
                    // Expected output: [ TutorThreeFirst Bachelor 3 years 1 ]
                    System.out.println("**************************************************** Search result   :   "
                            +TutorUtilFunctions.findTopTutorsHavingDegreeAndNPlusExperiances.apply(users,"Bachelor","3 years",4)
                            +" ****************************************************");
                    System.out.println("");
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("**************************************************************************************************************************************************************");
                    break;
                case 'f':
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("************************************ Looking for top young female tutors ... ****************");
                    System.out.println("");
                    // Expected output: [TutorEightFirst TutorEightLast Female +16415895608 MotiveEight ]
                    System.out.println("**************************************************** Search result   :   "
                            +TutorUtilFunctions.tutorsWithAgeRatingOf3.apply(users,"Female",27,4)
                            +" ****************************************************");
                    System.out.println("");
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("**************************************************************************************************************************************************************");
                    break;

                case 'g':
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("************************************ Looking for top total number of subscribed parents ... ****************");
                    System.out.println("");
                    // Expected output: [4]
                    System.out.println("**************************************************** Search result   :   "
                            +TutorUtilFunctions.getTotalSubscribedParents.apply(users)
                            +" ****************************************************");
                    System.out.println("");
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("**************************************************************************************************************************************************************");
                    break;
                case 'h':
                    System.out.println("**************************************************************************************************************************************************************");
                    System.out.println("************************************ Looking for tutor 7 years of experienced in maths ... ****************");
                    System.out.println("");
                    // Expected output: [TutorSevenFirst, Math, Diploma, 7 years, 4]
                    System.out.println("**************************************************** Search result   :   "
                            +TutorUtilFunctions.findTutorsHavingExperianceOnMajor.apply(users,"Math","7 years",4)
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
