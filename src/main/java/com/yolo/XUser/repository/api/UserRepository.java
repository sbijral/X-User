package com.yolo.XUser.repository.api;

import com.yolo.XUser.resources.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>,UserRepositoryCustom {
}
