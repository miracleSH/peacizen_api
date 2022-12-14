package com.peacizen.peacizen_api.user.exception;

import com.peacizen.peacizen_api.support.exception.BussineseException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExist extends BussineseException {

    public UserAlreadyExist(String message){
        super(message, null, null);
    }
}
