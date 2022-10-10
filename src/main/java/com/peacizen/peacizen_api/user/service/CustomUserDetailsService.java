package com.peacizen.peacizen_api.user.service;

import com.peacizen.peacizen_api.domain.user.User;
import com.peacizen.peacizen_api.domain.user.UserRepository;
import com.peacizen.peacizen_api.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException(MessageUtil.getMessage("USER_NOT_FOUND")));
    }

    private UserDetails createUserDetails(User user){
        return User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
