package com.ood.OODPro.Payload.Request;

import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
    @NotBlank(message = "Phone Number is mandatory")
    @Size(min = 3, max = 10,message = "Phone Number must be between 3 and 10 characters")
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Size(max = 50, message = "Email must be between 3 and 50 characters")
    @Email
    private String emailId;

    @NotBlank(message = "Full Name is mandatory")
    @Size(max = 50, message = "Full Name must be between 3 and 50 characters")
    private String fullName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
    private String password;


}
