package com.yolo.XUser.repository.api;

import com.yolo.XUser.resources.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City,String> {

}
