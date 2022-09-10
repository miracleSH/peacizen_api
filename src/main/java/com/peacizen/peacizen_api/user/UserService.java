package com.peacizen.peacizen_api.user;

import com.peacizen.peacizen_api.domain.user.User;
import com.peacizen.peacizen_api.domain.user.UserRepository;
import com.peacizen.peacizen_api.user.dto.RegisterRequest;
import com.peacizen.peacizen_api.user.exception.UserAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;

    public User register(RegisterRequest registerRequest){

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new UserAlreadyExist(messageSourceAccessor.getMessage("emailDuplicate", Locale.KOREA));
        }

        return userRepository.save(User.builder()
        .name(registerRequest.getName())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .postcode(registerRequest.getAddress().getPostcode())
        .address(registerRequest.getAddress().getAddress())
        .addressDetail(registerRequest.getAddress().getDetail())
        .phone(registerRequest.getPhone())
        .build());
    }


}
