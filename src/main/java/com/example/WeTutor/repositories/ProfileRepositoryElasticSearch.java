package com.example.WeTutor.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.WeTutor.entities.Profile;

@Repository
public interface ProfileRepositoryElasticSearch extends ElasticsearchRepository<Profile, String> {

}
