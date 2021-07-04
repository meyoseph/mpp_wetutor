package com.example.WeTutor.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.WeTutor.entities.Profile;

@Repository
public interface ProfileRepositoryElasticSearch extends ElasticsearchRepository<Profile, String> {

	Profile findAllByUserName(String tutorUserName);

	Profile getProfileByTutorLocation(String tutorLocation);

	Profile getProfileByTutorSubjects(String tutorSubjects);

	Profile getProfileByTutorLanguages(String tutorLanguages);

}
