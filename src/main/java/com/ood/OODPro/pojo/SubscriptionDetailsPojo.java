package com.ood.OODPro.pojo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Collection;

public class SubscriptionDetailsPojo {

    private Integer id;

    private String userId;
    private String subscriptionName;

    private Float subscriptionPrice;

    private String billingCycle;

    private Date billingDate;

    private String sendReminder;

    private String note;

    public SubscriptionDetailsPojo(String userId, String subscriptionName,
                                   Float subscriptionPrice,
                                   String billingCycle,
                                   Date billingDate,
                                   String sendReminder,
                                   String note,
                                   Collection<? extends GrantedAuthority> authorities
    ) {

        this.userId=userId;
        this.subscriptionName = subscriptionName;
        this.subscriptionPrice = subscriptionPrice;
        this.billingCycle = billingCycle;
        this.billingDate = billingDate;
        this.sendReminder = sendReminder;
        this.note =note;
    }
}
