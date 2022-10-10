package com.peacizen.peacizen_api.support.exception;

import com.peacizen.peacizen_api.util.MessageUtil;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ApiError {

    @NonNull
    private HttpStatus httpStatus;

    @NonNull
    private String timeStamp;

    private String exceptionName;

    private String debugMessage;
    private String warningMessage = MessageUtil.getMessage("SERVER_FAIL");

    private List<FieldError> fieldErrorList;

    public ApiError(@NonNull HttpStatus httpStatus, @NonNull String timeStamp, String debugMessage, String warningMessage, String exceptionName){
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
        this.debugMessage = debugMessage;
        this.warningMessage = warningMessage;
        this.exceptionName = exceptionName;
    }
}
