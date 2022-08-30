package com.peacizen.peacizen_api.user;

import com.peacizen.peacizen_api.domain.user.User;
import com.peacizen.peacizen_api.domain.user.UserRepository;
import com.peacizen.peacizen_api.user.dto.RegisterRequest;
import com.peacizen.peacizen_api.user.exception.UserAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest registerRequest, Errors errors){

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            errors.rejectValue("email", "등록되어 있는 이메일입니다.");
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
