package com.peacizen.peacizen_api.user;

import com.peacizen.peacizen_api.domain.user.User;
import com.peacizen.peacizen_api.domain.user.UserRepository;
import com.peacizen.peacizen_api.user.dto.RegisterRequest;
import com.peacizen.peacizen_api.user.exception.UserAlreadyExist;
import com.peacizen.peacizen_api.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest registerRequest){

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new UserAlreadyExist(MessageUtil.getMessage("EMAIL_DUPLICATED"));
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
