package com.ood.OODPro.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@Getter
@Setter
public class UserDetailsPojo extends User {
    private long id;
    private String emailId;
    private String phoneNumber;
    private String fullName;
    private String password;


    public UserDetailsPojo(String phoneNumber,
                           String emailId,
                           String fullName,
                           String password,
                           Collection<? extends GrantedAuthority> authorities
    ) {
        super(emailId, "password", authorities);

        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.emailId = emailId;
        this.password =password;
    }

}
