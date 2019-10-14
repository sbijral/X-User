package com.yolo.XUser.resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "City")
public class City {

    @Id
    private String cityId;
    private String cityName;
}
