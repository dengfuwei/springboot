package com.itopener.demo.es.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.itopener.demo.es.model.User;

public interface UserRepository extends ElasticsearchRepository<User, Long> {

}
