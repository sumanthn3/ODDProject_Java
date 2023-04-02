package com.ood.OODPro.Payload.Request;

import javax.validation.constraints.NotBlank;

public class SigninRequest {
    @NotBlank
    private String emailId;

    @NotBlank
    private String password;

    public String getEmail() {
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
