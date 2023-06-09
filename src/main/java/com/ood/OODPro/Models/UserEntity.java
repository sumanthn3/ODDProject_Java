package com.ood.OODPro.Models;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phone_number"),
                @UniqueConstraint(columnNames = "email_id")
        })
@Data
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "phone_number", unique = true)

    private String phoneNumber;

    @Column(name = "password")
    private String password;



    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;




}
