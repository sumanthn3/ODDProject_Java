package com.ood.OODPro.Models;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "users_subscriptions",uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
@Data
public class UserSubscriptionsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "subscription_name")
    private String subscriptionName;

    @Column(name = "subscription_price")
    private Float subscriptionPrice;

    @Column(name = "billing_cycle")
    private String billingCycle;

    @Column(name = "billing_date")
    private Date billingDate;

    @Column(name = "send_reminder")
    private String sendReminder;


    @Column(name = "note")
    private String note;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
