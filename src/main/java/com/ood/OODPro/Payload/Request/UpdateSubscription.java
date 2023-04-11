package com.ood.OODPro.Payload.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class UpdateSubscription {

    private Integer id;

    @NotBlank
    private String subscriptionName;


    private Float subscriptionPrice;

    @NotBlank
    private String billingCycle;


    private Date billingDate;

    @NotBlank
    private String sendReminder;

    private String note;
}
