package com.example.WeTutor.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.WeTutor.entities.Profile;

public interface ProfileRepositoryElasticSearch extends ElasticsearchRepository<Profile, String> {

}
