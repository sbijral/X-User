package com.yolo.XUser.resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Category")
public class Category {
    @Id
    private String categoryId;
    private String categoryName;
}
