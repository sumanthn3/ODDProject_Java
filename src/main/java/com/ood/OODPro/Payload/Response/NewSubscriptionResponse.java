package com.ood.OODPro.Payload.Response;
public class NewSubscriptionResponse {
    private String message;

    public NewSubscriptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
