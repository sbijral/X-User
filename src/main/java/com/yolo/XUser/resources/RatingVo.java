package com.yolo.XUser.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingVo {

    private String raterId;
    private Double rating;

    public RatingVo(String userId, Double rating) {
    }

    @Override
    public boolean equals(Object obj) {
        return this.raterId.equals(((RatingVo)obj).getRaterId());
    }
}
