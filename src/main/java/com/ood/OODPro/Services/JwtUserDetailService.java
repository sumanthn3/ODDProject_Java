package com.ood.OODPro.Services;


import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.pojo.UserDetailsPojo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


@Slf4j
@Service
@Component
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsPojo userDetailsPojo;

    @Autowired
    private UserService userService;



    @SneakyThrows
    @Override
    public UserDetailsPojo loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("getUserByUsername --> "+ username);
        String mobile = "";
        long id = 0;
        UserEntity userEntity= userService.getUserEntityWithEmail(username);
        userDetailsPojo = new UserDetailsPojo(
                userEntity.getPhoneNumber(),
                userEntity.getEmailId(),
                userEntity.getName(),
                userEntity.getPassword(),
                new ArrayList<>()
        );

        return userDetailsPojo;
    }


}