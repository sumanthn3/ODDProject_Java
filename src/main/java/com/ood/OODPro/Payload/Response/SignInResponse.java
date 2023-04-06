package com.ood.OODPro.Payload.Response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignInResponse {
    private Long id;
    private String phoneNumber;
    private String emailId;

    private String fullName;

    private String token;

    public SignInResponse(Long id, String emailId, String phoneNumber, String fullName,String token) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.fullName = fullName;
        this.token = token;
    }


}