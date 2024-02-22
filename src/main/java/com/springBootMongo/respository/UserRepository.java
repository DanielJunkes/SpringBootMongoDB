package com.springBootMongo.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springBootMongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
}
