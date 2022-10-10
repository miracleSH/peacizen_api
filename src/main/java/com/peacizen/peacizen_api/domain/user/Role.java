package com.peacizen.peacizen_api.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_no")
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    @Getter
    public enum RoleType{
        ROLE_ADMIN(100L, "관리자", "ADMIN"),
        ROLE_USER(200L, "회원", "USER");

        Long roleNo;
        String korName;
        String engName;

        RoleType(Long roleNo, String korName, String engName){
            this.roleNo = roleNo;
            this.korName = korName;
            this.engName = engName;
        }
    }

    public Role(RoleType roleType){
        this.id = roleType.getRoleNo();
        this.roleType = roleType;
    }


}
