package com.example.WeTutor.functional;

import com.example.WeTutor.entities.Parent;
import com.example.WeTutor.entities.ProfileState;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TutorUtilFunctions {

    // ************************* QUERY ONE **********************
    /**
     * @return Parents' email which are active or has already subscribed
     */
    public static final Function<List<User>, List<String>> getActiveParents = (usersList) ->
            usersList.stream()
                    .flatMap(u -> u.getRoles().stream())
                    .filter(r -> r instanceof Parent)
                    .map(r -> (Parent) r)
                    .filter(p -> p.isActive())
                    .map(p -> p.getEmail())
                    .collect(Collectors.toList());

    // ************************* QUERY TWO **********************

    /**
     * @return true or false which is used to check if an instance is of tutor or parent type ... @Helper function
     * @params User
     */
    public static final Function<User, Boolean> isTutor = (user) ->
            user.getRoles().get(0) instanceof Tutor;

    /**
     * @return Tutors' first name and last name with approved profile, having at least n rating, and of top K
     * @params n: having at least rating of value n
     * @params k: top k tutors sorted with their ratings
     */
    public static final TriFunction<List<User>, Integer, Long, List<String>> findTopKTutors = (usersList, n, k) ->
            usersList.stream()
                    .filter(u -> isTutor.apply(u))
                    .map(u -> u.getProfile())
                    .filter(p -> p.getProfileState().equals(ProfileState.APPROVED) && p.getRating() >= n)
                    .sorted((s1,s2) -> s2.getRating() - s1.getRating())
                    .limit(k)
                    .map(p -> p.getFirstName()+" "+p.getLastName())
                    .collect(Collectors.toList());


    // ************************************** QUERY THREE **********************************************
    /**
     * @return true or false which is used to check if a given language is in the comma separated list of languages or not ... @Helper function
     */
    public static BiFunction<String, String, Boolean> existenceHelperFunction = (list, element) ->
            Arrays.stream(list.split(",")).anyMatch(l -> l.contains(element));

    /**
     * @return Tutors' first name and last name with approved profile, not older than m, specializing in s, and who can speak l
     * @params age : not older than this value
     * @params subject : Specializing in a subject is the same as having a major in that subject
     * @params language: who can speak this language
     */
    public static QuadFunction<List<User>, Integer, String, String, List<String>> tutorWithMajorSubjectAndLanguage = (usersList,age, subject, language) ->
            usersList.stream()
                    .filter(u -> isTutor.apply(u))
                    .map(u -> u.getProfile())
                    .filter(p -> (p.getProfileState().equals(ProfileState.APPROVED) &&
                            Integer.parseInt(p.getAge()) < age)
                            && p.getMajorSubject().equals(subject)
                            && existenceHelperFunction.apply(p.getLanguages(), language))
                    .map(p -> p.getFirstName()+" "+p.getLastName())
                    .collect(Collectors.toList());

}
