package com.peacizen.peacizen_api.user.exception;


import com.peacizen.peacizen_api.support.exception.BussineseException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFountException extends BussineseException {

    public UserNotFountException(String message){
        super(message, null, null);
    }
}
