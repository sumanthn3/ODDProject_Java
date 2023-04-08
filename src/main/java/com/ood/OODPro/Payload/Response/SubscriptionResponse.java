package com.ood.OODPro.Payload.Response;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class SubscriptionResponse {

    private Integer id;
    private String subscriptionName;
    private Float subscriptionPrice;

    private String billingCycle;


    private Date billingDate;

    private String sendReminder;

    private String note;

    public SubscriptionResponse(Integer id, String subscriptionName,Float subscriptionPrice, String billingCycle, Date billingDate, String sendReminder,String note) {
        this.id = id;
        this.subscriptionName = subscriptionName;
        this.subscriptionPrice = subscriptionPrice;
        this.billingCycle = billingCycle;
        this.billingDate = billingDate;
        this.sendReminder = sendReminder;
        this.note = note;
    }
}
