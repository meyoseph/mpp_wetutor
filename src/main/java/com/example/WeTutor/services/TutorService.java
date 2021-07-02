package com.example.WeTutor.services;

import com.example.WeTutor.entities.*;
import com.example.WeTutor.repositories.*;
import com.example.WeTutor.requests.FeedbackRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TutorService {

    TutorRepository tutorRepository;
    ParentRepository parentRepository;
    UserRepository userRepository;
    FeedbackRepository feedbackRepository;
    ProfileRepository profileRepository;

    public ResponseEntity<Object> getAllTutors() {
        JSONObject responseObject = new JSONObject();

        List<Tutor> tutors = tutorRepository.findAll();
        if(tutors != null){
            responseObject.put("success",true);
            responseObject.put("tutors", tutors);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }else{
            responseObject.put("error",true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }

    }

    public ResponseEntity<Object> getTutor(String tutorId) {
        JSONObject responseObject = new JSONObject();

        Tutor tutor = tutorRepository.findTutorById(tutorId);

        responseObject.put("success",true);
        responseObject.put("tutor", tutor);

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public ResponseEntity<Object> rateTutor(FeedbackRequest feedbackRequest) {

        JSONObject responseObject = new JSONObject();

        if(!validateInputs(feedbackRequest.getRating()+""))
            responseObject.put("rating", "Rating is required");

        int ratings = 0;
        int ratedByCounter = 0;
        Tutor tutor = tutorRepository.findTutorByEmail(feedbackRequest.getTutorEmail());
        Parent parent = parentRepository.findParentByEmail(feedbackRequest.getParentEmail());

        User user = userRepository.findByEmail(feedbackRequest.getTutorEmail());

        Profile profile = profileRepository.findProfileByTutorId(user.getId());

        if(profile == null){
            responseObject.put("success",false);
            responseObject.put("message","You can not give rating for a tutor with no profile!");

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }
        Feedback feedback = new Feedback(parent, feedbackRequest.getRating(), tutor);
        feedbackRepository.save(feedback);

        List<Feedback> feedbacks = feedbackRepository.findAll();
        for(Feedback f: feedbacks){
            if(f.getTutor().getEmail().equals(feedbackRequest.getTutorEmail())){
                ratings += f.getRating();
                ratedByCounter++;
            }
        }

        profile.setRating(ratings / ratedByCounter);
        profile.setRatedBy(ratedByCounter);

        profileRepository.save(profile);

        responseObject.put("success",true);
        responseObject.put("message","Rating created successfully");

        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }

}
