package com.peacizen.peacizen_api.support.exception;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class BussineseException extends RuntimeException{

    private String warningMessage;
    private HashMap<String, Object> returnMap;
    private String addLog;

    public BussineseException(){super();}

    public BussineseException(String warningMessage, String addLog, HashMap<String, Object> returnMap){
        this.warningMessage= warningMessage;
        this.returnMap = returnMap;
        this.addLog = addLog;
    }

}
