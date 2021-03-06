package com.example.WeTutor.services;

import com.example.WeTutor.DTO.ProfileDto;
import com.example.WeTutor.entities.Profile;
import com.example.WeTutor.entities.Role;
import com.example.WeTutor.entities.Tutor;
import com.example.WeTutor.entities.User;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.ProfileRequest;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private ProfileService profileService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        profileService = new ProfileService(profileRepository, tutorRepository, userRepository, modelMapper);
    }

    @Test
    void itShouldInvokeGetProfiles(){
        ProfileService mockPofileService = mock(ProfileService.class);
        mockPofileService.getProfiles();
        verify(mockPofileService, times(1)).getProfiles();
    }

    @Test
    void itShouldReturnListOfProfiles(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        userRepository.save(user);
        User user2 = userRepository.findById(user.getId());
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                user2);
        profileRepository.save(profile);
        List<Profile> profileList = new ArrayList<>();
        profileList.add(profile);
       // when(profileRepository.findAll()).thenReturn(profileList);
        ResponseEntity<Object> responses = profileService.getAllProfiles();
        assertEquals("200 OK", responses.getStatusCode().toString());
    }

    @Test
    void itShouldInvokeUpdateProfiles(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "test",
                "1");
        ProfileService mockPofileService = mock(ProfileService.class);
        mockPofileService.updateProfile(profileRequest);
        verify(mockPofileService, times(1)).updateProfile(profileRequest);
    }

    @Test
    void itShouldReturnProfileDoesntExistErrorStatusCodeValue(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        Role role = new Tutor();
        User user = new User("test12", "test22", "test3@gmail.com", role);
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "test",
                user.getId());
        ResponseEntity<Object> responses = profileService.updateProfile(profileRequest);
        assertEquals(400, responses.getStatusCodeValue());
    }

    @Test
    void itShouldUpdateProfile(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "test",
                "1");
        profileService.updateProfile(profileRequest);
        profileService.updateProfile(profileRequest);
        verify(profileRepository, times(2)).findAll();
    }

    @Test
    void itShouldTestIfDeleteProfileIsInvoked(){
        ProfileService mockPofileService = mock(ProfileService.class);
        mockPofileService.deleteProfile("1");
        verify(mockPofileService, times(1)).deleteProfile("1");
    }

    @Test
    void itShouldReturnProfileDoesntExist(){
        ResponseEntity<Object> responses = profileService.deleteProfile("123");
        assertEquals(400, responses.getStatusCodeValue());
    }

    @Test
    void itShouldDeleteProfileIfProfileExist(){
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "subjects",
                "degree",
                "test",
                "languages",
                "test",
                new User());
        when(profileRepository.findProfileById(profile.getId())).thenReturn(profile);
        ResponseEntity<Object> responses = profileService.deleteProfile(profile.getId());
        assertEquals(200, responses.getStatusCodeValue());
    }

    @Test
    void itShouldDeleteProfile(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "test",
                "1");
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                new User());
        profileService.addProfile(profileRequest);
        given(profileRepository.findProfileByTutorId(profileRequest.getTutorId())).willReturn(profile);
        ResponseEntity<Object> responses = profileService.deleteProfile("1");
        System.out.println(profileRepository.findProfileById(profile.getId()));
    }

    @Test
    void itShouldAddProfile(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "test",
                "1");
        ResponseEntity<Object> responses = profileService.addProfile(profileRequest);
        assertEquals(200, responses.getStatusCodeValue());
    }

    @Test
    void itShouldAddProfileWithDefaultWhenProfilPicIsNull(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "1");
        ResponseEntity<Object> responses = profileService.addProfile(profileRequest);
        assertEquals(200, responses.getStatusCodeValue());
    }

    @Test
    void itShouldAddReturnBadRquest(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        ResponseEntity<Object> responses = profileService.addProfile(profileRequest);
        assertEquals(400, responses.getStatusCodeValue());
    }

    @Test
    void itShouldValidateAge(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("age", "Age is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateFirstName(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("first_name", "First name is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateLastName(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("last_name", "Last name is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateGender(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("gender", "Gender is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidatePhoneNumber(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "",
                "addis ababa",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("phone_number", "Phone number is not valid!");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateLocation(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("location", "Location is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateMotive(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("motive", "Motive is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateGetMajorSubject(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("major_subject", "Major Subject is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateSubject(){
        String[] subjects = new String[]{""};
        String[] languages = new String[]{"amharic", "english"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("subjects", "Subjects are required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateLanguage(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{""};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "math",
                subjects,
                "degree",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("languages", "Languages are required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateEducation(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"test"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "math",
                subjects,
                "",
                "test",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("educations", "Education is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldValidateWorkExpirence(){
        String[] subjects = new String[]{"ruby", "java"};
        String[] languages = new String[]{"test"};
        ProfileRequest profileRequest = new ProfileRequest(
                "24",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "test",
                "test",
                "math",
                subjects,
                "test",
                "",
                languages,
                "1",
                "");
        JSONObject response = profileService.validateAll(profileRequest);
        JSONObject errorObject = new JSONObject();
        errorObject.put("work_experiences", "Work Experience is required");
        errorObject.put("tutor", "Tutor can not be null");
        assertThat(response).isEqualTo(errorObject);
    }

    @Test
    void itShouldReturnBadRequestWhenProfileISNull(){
        ResponseEntity<Object> responseEntity = profileService.request("1");
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldSendApprovalRequestSent(){
        Profile profile = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "subjects",
                "degree",
                "test",
                "languages",
                "test",
                new User());
        when(profileRepository.findProfileById(profile.getId())).thenReturn(profile);
        ResponseEntity<Object> responseEntity = profileService.request(profile.getId());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldReturnWhenGetProfilesISCalled(){
        ResponseEntity<Object> responseEntity = profileService.getProfiles();
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldGetProfiles(){
        List<Profile> list = new ArrayList<Profile>();
        Profile profile1 = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                new User());
        Profile profile2 = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                new User());
        list.add(profile1);
        list.add(profile2);
        given(profileRepository.findAll()).willReturn(list);
        List<ProfileDto> profileDtos = mapList(list, ProfileDto.class);
        given(profileService.mapList(list, ProfileDto.class)).willReturn(profileDtos);
        ResponseEntity<Object> responseEntity = profileService.getProfiles();
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    <S,T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    @Test
    void itShouldFailToReturnProfile(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        Profile profile1 = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                new User());
        String tutorId = "1";
        given(userRepository.findById(tutorId)).willReturn(user);
        given(profileRepository.findProfileByTutorId(tutorId)).willReturn(profile1);
        ResponseEntity<Object> responseEntity = profileService.getProfileById(tutorId);
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldReturnProfile(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        Profile profile1 = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                new User());
        when(userRepository.findById(user.getId())).thenReturn(user);
        when(profileRepository.findProfileByTutorId(user.getId())).thenReturn(profile1);
        ResponseEntity<Object> responseEntity = profileService.getProfileById(user.getId());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void itShouldReturnBadRequestWhenProfileDoesExist(){
        Role role = new Tutor();
        User user = new User("test1", "test2", "test@gmail.com", role);
        given(userRepository.save(user)).willReturn(user);
        User user2 = new User("test1", "test2", "test@gmail.com", role);
        given(userRepository.save(user)).willReturn(user2);
        Profile profile1 = new Profile(
                "27",
                "yoseph",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                user);
        Profile profile2 = new Profile(
                "30",
                "meskerem",
                "birhanu",
                "male",
                "+251911072790",
                "addis ababa",
                "test",
                "math",
                "java, ruby",
                "degree",
                "test",
                "test",
                "test",
                user2);
        //List<Profile> profiles = new ArrayList<>();
        //profiles.add(profile1);
        //profiles.add(profile2);
        //when(profileRepository.findAll()).thenReturn(profiles);
        user.setId("123");
        user2.setId("456");
        profile1.setTutor(user);
        profile2.setTutor(user2);
        ResponseEntity<Object> responseEntity = profileService.deleteProfileByTutorId(user.getId());
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

//    @Test
//    void itShouldGetTheProfileOfLoggedInUser(){
//        Role role = new Tutor();
//        User user = new User("test1", "test2", "test@gmail.com", role);
//        user.setId("123");
//        when(profileService.getLoggedInUserId()).thenReturn(user.getId());
//        when(userRepository.findById(user.getId())).thenReturn(user);
//        Profile profile1 = new Profile(
//                "27",
//                "yoseph",
//                "birhanu",
//                "male",
//                "+251911072790",
//                "addis ababa",
//                "test",
//                "math",
//                "java, ruby",
//                "degree",
//                "test",
//                "test",
//                "test",
//                user);
//        when(profileRepository.findProfileByTutorId(user.getId())).thenReturn(profile1);
//        ResponseEntity<Object> responseEntity = profileService.getProfile();
//        System.out.println(responseEntity);
//    }

}
