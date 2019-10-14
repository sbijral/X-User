package com.yolo.XUser.repository.api;

import com.yolo.XUser.resources.Category;
import com.yolo.XUser.resources.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {

}
