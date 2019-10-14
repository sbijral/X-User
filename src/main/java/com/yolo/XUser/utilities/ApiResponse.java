package com.yolo.XUser.utilities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    private T responseObject;
    private ApiResponseStatus apiResponseStatus;

    public ApiResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ApiResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiResponse(HttpStatus status, Throwable exception) {
        this();
        this.status = status;
        this.message = "UnExpected Error";
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiResponse(HttpStatus status, String message, Throwable exception) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, T responseObject) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        this.responseObject = responseObject;
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus) {
        this();
        this.apiResponseStatus = apiResponseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public ApiResponseStatus getApiResponseStatus() {
        return apiResponseStatus;
    }

    public void setApiResponseStatus(ApiResponseStatus apiResponseStatus) {
        this.apiResponseStatus = apiResponseStatus;
    }


}


