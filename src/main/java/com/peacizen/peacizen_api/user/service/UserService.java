package com.peacizen.peacizen_api.user.service;

import com.peacizen.peacizen_api.config.jwt.JwtTokenProvider;
import com.peacizen.peacizen_api.domain.user.Role;
import com.peacizen.peacizen_api.domain.user.User;
import com.peacizen.peacizen_api.domain.user.UserRepository;
import com.peacizen.peacizen_api.user.dto.LoginRequest;
import com.peacizen.peacizen_api.user.dto.RegisterRequest;
import com.peacizen.peacizen_api.user.dto.TokenInfo;
import com.peacizen.peacizen_api.user.dto.UserResponse;
import com.peacizen.peacizen_api.user.exception.UserAlreadyExist;
import com.peacizen.peacizen_api.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     *
     * @param registerRequest
     * @return
     */
    public UserResponse register(RegisterRequest registerRequest) {

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExist(MessageUtil.getMessage("EMAIL_DUPLICATED"));
        }
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .postcode(registerRequest.getAddress().getPostcode())
                .address(registerRequest.getAddress().getAddress())
                .addressDetail(registerRequest.getAddress().getDetail())
                .phone(registerRequest.getPhone())
                .roles(new HashSet<>())
                .build();

        user.addRoles(new Role(Role.RoleType.ROLE_USER));

        return new UserResponse(userRepository.save(user), null);
    }

    public UserResponse login(LoginRequest loginRequest) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3. 인증정보를 기반으로 JWT 토큰 생성
        return new UserResponse((User)authentication.getDetails(),jwtTokenProvider.generateToken(authentication));
    }


}
