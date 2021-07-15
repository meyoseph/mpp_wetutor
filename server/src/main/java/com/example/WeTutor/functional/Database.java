package com.example.WeTutor.functional;

import com.example.WeTutor.entities.*;

import java.util.List;

public class Database {
    private static List<User> users;
    private static List<Profile> profiles;


    public static Role createRole (String email, boolean isTutor) {
        Role role;
        if(isTutor){
            role = new Tutor(email);
        }else{
            role = new Parent(email);
        }
        return role;
    }

    public static void populateTutorAndParents(){

        // ************* Users ***********
        // Populate both tutors and parents
        // ****************************************
        users = List.of(
            new User("One", "tutorPassword1", "tutorOne@gmail.com",createRole("tutorOne@gmail.com",true)),
            new User("Two", "tutorPassword2", "tutorTwo@gmail.com",createRole("tutorTwo@gmail.com",true)),
            new User("Three", "tutorPassword3", "tutorThree@gmail.com",createRole("tutorThree@gmail.com",true)),
            new User("Four", "tutorPassword4", "tutorFour@gmail.com",createRole("tutorFour@gmail.com",true)),
            new User("Five", "tutorPassword5", "tutorFive@gmail.com",createRole("tutorFive@gmail.com",true)),
            new User("Six", "tutorPassword6", "tutorSix@gmail.com",createRole("tutorSix@gmail.com",true)),
            new User("Seven", "tutorPassword7", "tutorSeven@gmail.com",createRole("tutorSeven@gmail.com",true)),
            new User("Eight", "tutorPassword8", "tutorEight@gmail.com",createRole("tutorEight@gmail.com",true)),

            new User("Nine", "parentPassword1", "parentOne@gmail.com",createRole("parentOne@gmail.com",false)),
            new User("Ten", "parentPassword2", "parentTwo@gmail.com",createRole("parentTwo@gmail.com",false)),
            new User("Eleven", "parentPassword3", "parentThree@gmail.com",createRole("parentThree@gmail.com",false)),
            new User("Twelve", "parentPassword4", "parentFour@gmail.com",createRole("parentFour@gmail.com",false)),
            new User("Thirteen", "parentPassword5", "parentFive@gmail.com",createRole("parentFive@gmail.com",false)),
            new User("Fourteen", "parentPassword6", "parentSix@gmail.com",createRole("parentSix@gmail.com",false)),
            new User("Fifteen", "parentPassword7", "parentSeven@gmail.com",createRole("parentSeven@gmail.com",false)),
            new User("Sixteen", "parentPassword8", "parentEight@gmail.com",createRole("parentEight@gmail.com",false))
        );

    }

    public static void buildTutorsProfile(){
        // ************* Tutors Profile ***********
        // Add profiles for tutors
        // ****************************************
        profiles = List.of(
                new Profile("21", "TutorOneFirst", "TutorOneLast", "Male", "+16415195601","LocationOne",
                        "MotiveOne", "English","IT,English,Biology", "Bachelor",
                        "1 years","English,Espagnol,Turkish", "No profile pic", users.get(0)),

                new Profile("22", "TutorTwoFirst", "TutorTwoLast", "Female", "+16415295602","LocationTwo",
                        "MotiveTwo", "Biology","Chemistry,English,Biology", "Diploma",
                        "2 years","Italian,Turkish", "No profile pic", users.get(1)),

                new Profile("23", "TutorThreeFirst", "TutorThreeLast", "Male", "+16415395603","LocationThree",
                        "MotiveThree", "Information Technology","Geography,English,Biology", "Bachelor",
                        "3 years","Espagnol,English", "No profile pic", users.get(2)),

                new Profile("24", "TutorFourFirst", "TutorFourLast", "Male", "+16415495604","LocationFour",
                        "MotiveFour", "English","Physics,English,Biology", "Masters",
                        "4 years","German,Espagnol,Turkish", "No profile pic", users.get(3)),

                new Profile("25", "TutorFiveFirst", "TutorFiveLast", "Female", "+16415595605","LocationFive",
                        "MotiveFive", "Biology","Physics,English,Biology", "Certificate",
                        "5 years","English,Espagnol,Turkish", "No profile pic", users.get(4)),

                new Profile("26", "TutorSixFirst", "TutorSixLast", "Female", "+16415695606","LocationSix",
                        "MotiveSix", "IT","Math,English,Biology", "Bachelor",
                        "6 years","English,Spanish,Turkish", "No profile pic", users.get(5)),

                new Profile("27", "TutorSevenFirst", "TutorSevenLast", "Male", "+16415795607","LocationSeven",
                        "MotiveSeven", "Math","Physics,English,Biology", "Diploma",
                        "7 years","Spanish,Espagnol,Turkish", "No profile pic", users.get(6)),

                new Profile("28", "TutorEightFirst", "TutorEightLast", "Female", "+16415895608","LocationEight",
                        "MotiveEight", "Information Technology","Physics,Math,Biology", "Masters",
                        "8 years","German,Espagnol,Turkish", "No profile pic", users.get(7))
        );
        // Give ratings before adding profile to tutors
        giveRatings();
        users.get(0).setProfile(profiles.get(0));
        users.get(1).setProfile(profiles.get(1));
        users.get(2).setProfile(profiles.get(2));
        users.get(3).setProfile(profiles.get(3));
        users.get(4).setProfile(profiles.get(4));
        users.get(5).setProfile(profiles.get(5));
        users.get(6).setProfile(profiles.get(6));
        users.get(7).setProfile(profiles.get(7));
    }

    public static void approveTutors(){
        // ************* Tutor Approval ***********
        // Approve some of the tutors' profiles
        // ****************************************
        // Approve Tutor One
        users.get(0).getRoles().get(0).setActive(true);
        profiles.get(0).setProfileState(ProfileState.APPROVED);

        // Approve Tutor Three
        users.get(2).getRoles().get(0).setActive(true);
        profiles.get(2).setProfileState(ProfileState.APPROVED);

        // Approve tutor Five
        users.get(4).getRoles().get(0).setActive(true);
        profiles.get(4).setProfileState(ProfileState.APPROVED);

        // Approve Tutor Seven
        users.get(6).getRoles().get(0).setActive(true);
        profiles.get(6).setProfileState(ProfileState.APPROVED);

        // Approve Tutor Eight
        users.get(7).getRoles().get(0).setActive(true);
        profiles.get(7).setProfileState(ProfileState.APPROVED);
    }

    public static void activateParents(){
        // ************* Parent Activation ***********
        // Activate some of the parents' account
        // *****************************************
        // Approve Parent Two
        users.get(9).getRoles().get(0).setActive(true);

        // Approve Parent Four
        users.get(11).getRoles().get(0).setActive(true);

        // Approve Parent Six
        users.get(13).getRoles().get(0).setActive(true);

        // Approve Parent Eight
        users.get(15).getRoles().get(0).setActive(true);
    }

    public static void giveRatings(){
        // ************* Tutor profile rating ***********
        // Give ratings for all the tutors
        // **********************************************

        // Rate Tutor One
        profiles.get(0).setRating(4);
        profiles.get(0).setRatedBy(3);

        // Rate Tutor Two
        profiles.get(1).setRating(5);
        profiles.get(1).setRatedBy(1);

        // Rate Tutor Three
        profiles.get(2).setRating(1);
        profiles.get(2).setRatedBy(1);

        // Rate Tutor Four
        profiles.get(3).setRating(2);
        profiles.get(3).setRatedBy(4);

        // Rate Tutor Five
        profiles.get(4).setRating(2);
        profiles.get(4).setRatedBy(3);

        // Rate Tutor Six
        profiles.get(5).setRating(3);
        profiles.get(5).setRatedBy(4);

        // Rate Tutor Seven
        profiles.get(6).setRating(4);
        profiles.get(6).setRatedBy(3);

        // Rate Tutor Eight
        profiles.get(7).setRating(5);
        profiles.get(7).setRatedBy(6);

    }
    public static void connect(){
        System.out.println();
        // Starting setting up and preparing our mock database
        System.out.println("Setting up mock database and populating, please wait ...");

        // Create tutors and parents
        populateTutorAndParents();
        // Build profiles for tutors
        buildTutorsProfile();
        // Approve tutors profile
        approveTutors();
        // Activate parents account
        activateParents();

        // End of setting up and preparing our mock database
        System.out.println("Done populating.");
        System.out.println("Database ready for access.");
        System.out.println();

    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Profile> getProfiles() {
        return profiles;
    }
}
