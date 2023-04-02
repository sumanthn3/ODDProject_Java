package com.ood.OODPro.Payload.Request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 10)
    private String phoneNumber;

    @NotBlank
    @Size(max = 50)
    @Email
    private String emailId;

    @NotBlank
    @Size(max = 50)
    @Email
    private String fullName;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmailId() {
        return emailId;
    }

    public void setEmail(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
