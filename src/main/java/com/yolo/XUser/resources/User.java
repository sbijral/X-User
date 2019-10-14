package com.yolo.XUser.resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@Document(collection = "User")
public class User {

    @Id
    private String userId;
    private List<String> followers;
    private List<String> following;
    private List<RatingVo> ratings;
    private List<String> sharedMobileNoUsers;
    private List<String> sharedEmailUsers;
    private List<String> pendingFollowApprovals;
    @Field
    private Double aggregateRating=0D;



}
