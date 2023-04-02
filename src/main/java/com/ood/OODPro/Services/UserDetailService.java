package com.ood.OODPro.Services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.pojo.UserDetailsPojo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Slf4j
@Service
@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsPojo userDetailsPojo;

    @Autowired
    private UserService userService;
    private static final long serialVersionUID = 1L;

//    private Long id;

    private String phoneNumber;
    private String fullName;
    private String emailId;

    @JsonIgnore
    private String password;



    public UserDetailService(String phoneNumber,String fullName, String emailId, String password) {

        this.phoneNumber = phoneNumber;
        this.fullName=fullName;
        this.emailId = emailId;
        this.password = password;
    }



//    @SneakyThrows
//    @Override
//    public UserDetailsPojo loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("getUserByUsername --> "+ username);
//        String mobile = "";
//        long id = 0;
//        UserEntity userEntity= userService.getUserEntityWithEmail(username);
//        userDetailsPojo = new UserDetailsPojo(
//                userEntity.getEmailId(),
//                userEntity.getName());
//        return userDetailsPojo;
//    }
//    public static UserDetailService build(UserEntity userEntity) {
//
//        return new UserDetailService(
//                UserEntity.getPhone(),
//                UserEntity.getUserName(),
//                UserEntity.getEmail(),
//                UserEntity.getUserPassword()
//                );
//    }

    @Override
    public String getName() {
        return fullName;
    }


    public String getEmail() {
        return emailId;
    }

    @Override
    public String getUserPassword() {
        return password;
    }

    @Override
    public String getPhone() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailService user = (UserDetailService) o;
        return Objects.equals(id, user.id);
    }

}