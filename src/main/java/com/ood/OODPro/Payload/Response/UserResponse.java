package com.ood.OODPro.Payload.Response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponse {
    private Integer id;
    private String phoneNumber;
    private String emailId;

    private String fullName;



    public UserResponse(Integer id, String phoneNumber, String emailId, String fullName) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.fullName = fullName;

    }


}