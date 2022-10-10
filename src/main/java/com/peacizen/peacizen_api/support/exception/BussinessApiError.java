package com.peacizen.peacizen_api.support.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class BussinessApiError extends ApiError {

    private HashMap<String, Object> returnMap;

    public BussinessApiError(@NonNull HttpStatus status, @NonNull String timeStamp, String debugMessage,
                              String warningMessage, String exceptionName, HashMap<String, Object> map) {
        super(status, timeStamp, debugMessage, warningMessage, exceptionName);
        this.returnMap = map;
    }


}
