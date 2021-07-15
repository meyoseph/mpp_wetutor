package com.example.WeTutor.functional;

import com.example.WeTutor.entities.Parent;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.*;

// creates the comparator for comparing rating values
class UserComparator implements Comparator<User> {

    // override the compare() method
    public int compare(User s1, User s2)
    {
        if (s1.getProfile().getRating() == s2.getProfile().getRating())
            return 0;
        else if (s1.getProfile().getRating() < s2.getProfile().getRating())
            return 1;
        else
            return -1;
    }
}
public class TutorUtilFunctionsTests {

    @BeforeEach
    public void setUp() {
        Database.connect();
    }

    /**
     * --------------------------------------
     */
    @Test
    public void testGetActiveParents(){
        List<User> users = Database.getUsers();

        List<String> parentsEmail = TutorUtilFunctions.getActiveParents.apply(users);
        List<String> parents = new ArrayList<>();

        for(User u: users){
            if(u.getRoles().get(0) instanceof Parent){
                if(u.getRoles().get(0).isActive()){
                    parents.add(((Parent) u.getRoles().get(0)).getEmail());
                }
            }
        }
        assertEquals(parentsEmail.size(), parents.size());
        assertEquals(parentsEmail, parents);
        assertTrue(parentsEmail.containsAll(parents));
    }

    @Test
    public void testIsTutor(){
        List<User> users = Database.getUsers();

        assertTrue(TutorUtilFunctions.isTutor.apply(users.get(2)));
        assertFalse(TutorUtilFunctions.isTutor.apply(users.get(11)));
        assertTrue(TutorUtilFunctions.isTutor.apply(users.get(0)).equals(users.get(0).getRoles().get(0) instanceof Tutor));

    }

    @Test
    public void testFindTopKTutors(){
        List<User> users = Database.getUsers();

        List<String> tutorsName = TutorUtilFunctions.findTopKTutors.apply(users, 4, 5L);
        List<User> tutors = new ArrayList<>();
        List<String> tutorsNameTwo = new ArrayList<>();

        for(User u: users){
            if(u.getRoles().get(0) instanceof Tutor){
                if(u.getRoles().get(0).isActive()){
                    if(u.getProfile().getRating() >= 4 ){
                        tutors.add(u);
                    }
                }
            }
        }
        Collections.sort(tutors, new UserComparator());
        for(User t: tutors){
            tutorsNameTwo.add(t.getProfile().getFirstName()+" "+t.getProfile().getLastName());
        }

        assertEquals(tutorsName.size(), tutorsNameTwo.size());
        assertEquals(tutorsName, tutorsNameTwo);
        assertTrue(tutorsNameTwo.containsAll(tutorsName));
    }

    @Test
    public void testExistenceHelperFunction(){
        String commaSeparatedList = "English,Physics,Biology";
        String input = "Biology";
        String inputTwo = "Chemistry";
        boolean exists = TutorUtilFunctions.existenceHelperFunction.apply(commaSeparatedList, input);
        boolean existsTwo = Arrays.asList(commaSeparatedList.split(",")).contains(input);

        assertTrue(TutorUtilFunctions.existenceHelperFunction.apply(commaSeparatedList,input));
        assertFalse(TutorUtilFunctions.existenceHelperFunction.apply(commaSeparatedList,inputTwo));
        assertTrue(exists && existsTwo);
    }

    @Test
    public void testTutorWithMajorSubjectAndLanguage(){
        List<User> users = Database.getUsers();

        List<String> tutorsName = TutorUtilFunctions.tutorWithMajorSubjectAndLanguage.apply(users, 25, "Information Technology", "English");
        List<String> tutorsNameTwo = new ArrayList<>();
        for(User u: users){
            if(u.getRoles().get(0) instanceof Tutor){
                if(u.getRoles().get(0).isActive()){
                    if(u.getProfile().getMajorSubject().equals("Information Technology") ){
                        if(Arrays.asList(u.getProfile().getLanguages().split(",")).contains("English")){
                            tutorsNameTwo.add(u.getProfile().getFirstName()+" "+u.getProfile().getLastName());
                        }
                    }
                }
            }
        }

        assertEquals(tutorsName.size(), tutorsNameTwo.size());
        assertEquals(tutorsName, tutorsNameTwo);
        assertTrue(tutorsNameTwo.containsAll(tutorsName));
    }

}
