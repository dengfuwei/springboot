package com.itopener.demo.mongodb.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itopener.demo.mongodb.model.User;

public interface UserRepository extends MongoRepository<User, Long> {

}
