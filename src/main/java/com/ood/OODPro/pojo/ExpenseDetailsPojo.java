package com.ood.OODPro.pojo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;


public class ExpenseDetailsPojo {

    private Integer id;

    private String userId;
    private String expenseName;

    private Float expensePrice;

    private Date expenseDate;

    private String category;

    private String description;



    public ExpenseDetailsPojo(String userId, String expenseName,
                                   Float expensePrice,
                                   Date expenseDate,
                                   String category,
                                   String description,
                                   Collection<? extends GrantedAuthority> authorities
    ) {

        this.userId=userId;
        this.expenseName = expenseName;
        this.expensePrice = expensePrice;
        this.expenseDate = expenseDate;
        this.category = category;
        this.description = description;
    }
}
