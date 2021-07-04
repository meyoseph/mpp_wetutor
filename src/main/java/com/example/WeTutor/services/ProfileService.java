package com.example.WeTutor.services;

import com.example.WeTutor.DTO.ProfileDto;
import com.example.WeTutor.entities.*;
import com.example.WeTutor.repositories.ProfileRepository;
import com.example.WeTutor.repositories.ProfileRepositoryElasticSearch;
import com.example.WeTutor.repositories.TutorRepository;
import com.example.WeTutor.repositories.UserRepository;
import com.example.WeTutor.requests.ProfileRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileService {
	
	@Autowired
	ProfileRepositoryElasticSearch profileRepositoryElasticSearch;

    ProfileRepository profileRepository;
    TutorRepository tutorRepository;
    UserRepository userRepository;

    private ModelMapper modelMapper;

    public ResponseEntity<Object> getProfiles() {
        JSONObject responseObject = new JSONObject();

        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDto> profileDto = mapList(profiles, ProfileDto.class);

        if(profiles != null && profileDto != null){
            responseObject.put("success",true);
            responseObject.put("profiles", profileDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            responseObject.put("error",true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    <S,T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Object> getAllProfiles() {
        JSONObject responseObject = new JSONObject();

        List<Profile> profiles = profileRepository.findAll();
        List<Profile> filteredProfiles = new ArrayList<Profile>();

        if(profiles != null){
            for(Profile p: profiles){
                if(p.getProfileState().equals("APPROVED")){
                    filteredProfiles.add(p);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(filteredProfiles);
        }else{
            responseObject.put("profile",false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public String getLoggedInUserId(){
        String userId = "";
        String username="";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        userId = userRepository.findByUserName(username).getId();
        return userId;
    }
    public ResponseEntity<Object> getProfile() {
        JSONObject responseObject = new JSONObject();
        String tutorId = getLoggedInUserId();
        User tutor = userRepository.findById(tutorId);
        Profile profile = profileRepository.findProfileByTutorId(tutorId);

        if(profile != null){
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        }else{
            responseObject.put("success",false);
            responseObject.put("message","No profile found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    public ResponseEntity<Object> addProfile(ProfileRequest profileRequest) {
        JSONObject responseObject = new JSONObject();
        String subjectsString = changeStringListToString(profileRequest.getSubjects());
        String languagesString = changeStringListToString(profileRequest.getLanguages());
        User tutor = userRepository.findById(profileRequest.getTutorId());
        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getTutor().equals(tutor)){
                responseObject.put("profile", "Profile has already been built");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
            }
        }
        responseObject = validateAll(profileRequest);

        if(responseObject.isEmpty()){
            String profilePic;
            if(profileRequest.getProfilePic().equals("")){
                profilePic = "https://www.gravatar.com/avatar/24fe3615bdba49bdf3e9ffb23f1b7bfd?s=200&r=pg&d=mm";
            }else{
                profilePic = profileRequest.getProfilePic();
            }
            Profile profile = new Profile(profileRequest.getAge(), profileRequest.getFirstName(),
                    profileRequest.getLastName(), profileRequest.getGender(),
                    profileRequest.getPhoneNumber(), profileRequest.getLocation(),
                    profileRequest.getMotive(), profileRequest.getMajorSubject(),
                    subjectsString, profileRequest.getEducations(), profileRequest.getWorkExperiences(),
                    languagesString, profilePic,tutor);
            profileRepository.save(profile);
            responseObject.put("success",true);
            responseObject.put("message","Profile created successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> updateProfile(ProfileRequest profileRequest) {
        JSONObject responseObject = new JSONObject();
        User tutor = userRepository.findById(profileRequest.getTutorId());
        String subjectsString = changeStringListToString(profileRequest.getSubjects());
        String languagesString = changeStringListToString(profileRequest.getLanguages());
        boolean flag = false;
        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getTutor().getId().equals(profileRequest.getTutorId())){
                flag = true;
            }
        }
        if(!flag) {
            responseObject.put("profile", "Profile doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        responseObject = validateAll(profileRequest);

        if(responseObject.isEmpty()){
            String profilePic;
            Profile profile = profileRepository.findProfileByTutorId(tutor.getId());
            if(profileRequest.getProfilePic().equals("")){
                profilePic = profile.getProfilePic();
            }else{
                profilePic = profileRequest.getProfilePic();
            }
            profile.setAge(profileRequest.getAge());
            profile.setFirstName(profileRequest.getFirstName());
            profile.setLastName(profileRequest.getLastName());
            profile.setGender(profileRequest.getGender());
            profile.setPhoneNumber(profileRequest.getPhoneNumber());
            profile.setLocation(profileRequest.getLocation());
            profile.setMotive(profileRequest.getMotive());
            profile.setMajorSubject(profileRequest.getMajorSubject());
            profile.setSubjects(subjectsString);
            profile.setEducations(profileRequest.getEducations());
            profile.setWorkExperiences(profileRequest.getWorkExperiences());
            profile.setLanguages(languagesString);
            profile.setProfilePic(profilePic);
            profile.setTutor(tutor);
            profileRepository.save(profile);
            responseObject.put("success", true);
            responseObject.put("message","Profile updated successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> deleteProfile(String profileId) {
        JSONObject responseObject = new JSONObject();

        boolean flag = false;
        Profile profile = profileRepository.findProfileById(profileId);
        if(profile == null) {
            responseObject.put("profile", "Profile doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        if(responseObject.isEmpty()){
            profileRepository.deleteProfileById(profileId);
            responseObject.put("success", true);
            responseObject.put("message","Profile deleted successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> deleteProfileByTutorId(String tutor_id) {
        JSONObject responseObject = new JSONObject();

        boolean flag = false;
        List<Profile> profiles = profileRepository.findAll();
        for(Profile profile: profiles ){
            if(profile.getTutor().getId().equals(tutor_id)){
                flag = true;
            }
        }
        if(!flag) {
            responseObject.put("profile", "Profile doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

        if(responseObject.isEmpty()){
            User tutor = userRepository.findById(tutor_id);
            Profile profile = profileRepository.findProfileByTutorId(tutor.getId());

            profileRepository.delete(profile);
            responseObject.put("success", true);
            responseObject.put("message","Profile updated successfully");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }

    public JSONObject validateAll(ProfileRequest profileRequest){
        JSONObject responseObject = new JSONObject();
        String subjectsString = changeStringListToString(profileRequest.getSubjects());
        String languagesString = changeStringListToString(profileRequest.getLanguages());

        if(!validateInputs(profileRequest.getAge())){
            responseObject.put("age", "Age is required");
        }
        if(!validateInputs(profileRequest.getFirstName())){
            responseObject.put("first_name", "First name is required");
        }
        if(!validateInputs(profileRequest.getLastName())){
            responseObject.put("last_name", "Last name is required");
        }
        if(!validateInputs(profileRequest.getGender())){
            responseObject.put("gender", "Gender is required");
        }
        if(!validateInputs(profileRequest.getPhoneNumber())){
            responseObject.put("phone_number", "Phone number is required");
        }
        if(!validateInputs(profileRequest.getLocation())){
            responseObject.put("location", "Location is required");
        }
        if(!validateInputs(profileRequest.getMotive())){
            responseObject.put("motive", "Motive is required");
        }
        if(!validateInputs(profileRequest.getMajorSubject())){
            responseObject.put("major_subject", "Major Subject is required");
        }
        if(!validateInputs(subjectsString)){
            responseObject.put("subjects", "Subjects are required");
        }
        if(!validateInputs(profileRequest.getEducations())){
            responseObject.put("educations", "Education is required");
        }
        if(!validateInputs(profileRequest.getWorkExperiences())){
            responseObject.put("work_experiences", "Work Experience is required");
        }
        if(!validateInputs(languagesString)){
            responseObject.put("languages", "Languages are required");
        }
        if(profileRequest.getTutorId() == "" || profileRequest.getTutorId() == null){
            responseObject.put("tutor", "Tutor can not be null");
        }

        return responseObject;
    }

    public String changeStringListToString (String[] list){
        String result = "";
        if (list.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s).append(",");
            }
            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        return result;
    }

    public ResponseEntity<Object> request(String profileId) {
        JSONObject response = new JSONObject();

        Profile profile = profileRepository.findProfileById(profileId);
        if(profile == null){
            response.put("status", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else{
            profile.setProfileState(ProfileState.PENDING);
            profileRepository.save(profile);
            response.put("status", true);
            response.put("message", "Approval request sent!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    public ResponseEntity<Object> getProfileById(String tutorId) {
        JSONObject response = new JSONObject();
        User tutor = userRepository.findById(tutorId);
        // Get profile using the tutor's id
        Profile profile = profileRepository.findProfileByTutorId(tutor.getId());
        if(profile == null){
            response.put("profile","No profile for this tutor");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

	public ResponseEntity<Object> getProfileByTutorUserName(String tutorUserName) {
		JSONObject response = new JSONObject();
		Profile profile = profileRepositoryElasticSearch.findAllByUserName(tutorUserName);
		if (profile == null) {
			response.put("profile","no profile by this username");
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(profile);
	}

	public ResponseEntity<Object> getProfileByTutorLocation(String tutorLocation) {
		JSONObject response =  new JSONObject();
		Profile profile = profileRepositoryElasticSearch.getProfileByTutorLocation(tutorLocation);
		if (profile == null) {
			 response.put("profile","no profile by this username");
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(profile);
	}

	public ResponseEntity<Object> getProfileByTutorSubjects(String tutorSubjects) {
		JSONObject response =  new JSONObject();
		Profile profile = profileRepositoryElasticSearch.getProfileByTutorSubjects(tutorSubjects);
		if (profile == null) {
			response.put("profile","no profile by this username");
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(profile);
	}

	public ResponseEntity<Object> getProfileByTutorLanguages(String tutorLanguages) {
		JSONObject response =  new JSONObject();
		Profile profile = profileRepositoryElasticSearch.getProfileByTutorLanguages(tutorLanguages);
		if (profile == null) {
			 response.put("profile","no profile by this username");
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(profile);
	}
}
