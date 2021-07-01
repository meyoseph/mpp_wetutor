package com.example.WeTutor.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.WeTutor.entities.Profile;

@Repository
public interface ProfileRepositoryElasticSearch extends ElasticsearchRepository<Profile, String> {

	List<Profile> findAllByUserName(String tutorUserName);

	List<Profile> getProfileByTutorLocation(String tutorLocation);

	List<Profile> getProfileByTutorSubjects(String tutorSubjects);

	List<Profile> getProfileByTutorLanguages(String tutorLanguages);

}
