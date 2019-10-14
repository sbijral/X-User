package com.yolo.XUser.utilities;

public class ApiResponseStatusToJson {
    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }
}

