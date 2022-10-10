package com.peacizen.peacizen_api.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtil {

    static MessageSourceAccessor messageSourceAccessor;

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor){
        MessageUtil.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String key){
        return messageSourceAccessor.getMessage(key);
    }
}
