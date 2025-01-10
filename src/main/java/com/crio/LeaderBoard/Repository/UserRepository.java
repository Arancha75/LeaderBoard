package com.crio.LeaderBoard.Repository;

import java.util.List;


import com.crio.LeaderBoard.Entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByScoreOrderByScoreDesc();

}
