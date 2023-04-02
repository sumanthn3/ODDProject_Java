package com.ood.OODPro.Payload.Response;

public class SignInResponse {
    private Long id;
    private String phoneNumber;
    private String emailId;

    public SignInResponse(Long id, String phoneNumber, String emailId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String email) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}