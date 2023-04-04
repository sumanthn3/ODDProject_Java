package com.ood.OODPro.Payload.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SigninRequest {
    @NotBlank
    private String emailId;

    @NotBlank
    private String password;


}
