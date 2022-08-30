package com.peacizen.peacizen_api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Address address;
    private String phone;


    @Getter
    @AllArgsConstructor
    public static class Address{
        private String postcode;
        private String address;
        private String detail;
    }
}
