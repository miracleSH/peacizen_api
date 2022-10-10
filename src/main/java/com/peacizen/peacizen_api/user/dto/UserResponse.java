package com.peacizen.peacizen_api.user.dto;

import com.peacizen.peacizen_api.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponse {

    private Long userNo;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String addressDetail;
    private TokenInfo tokenInfo;

    public UserResponse(User user, TokenInfo tokenInfo){
        this.userNo = user.getUserNo();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.addressDetail = user.getAddressDetail();
        this.tokenInfo = tokenInfo;
    }
}
