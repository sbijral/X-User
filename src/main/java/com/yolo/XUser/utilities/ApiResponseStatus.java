package com.yolo.XUser.utilities;

import com.fasterxml.jackson.annotation.JsonValue;

public class ApiResponseStatus {



    private final int code;
    private final String message;
    ApiResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public ApiResponseStatusToJson toJson() {
        ApiResponseStatusToJson apiResponseStatusToJson = new ApiResponseStatusToJson();
        apiResponseStatusToJson.setCode(this.code);
        apiResponseStatusToJson.setMessage(this.message);
        return apiResponseStatusToJson;
    }


}
