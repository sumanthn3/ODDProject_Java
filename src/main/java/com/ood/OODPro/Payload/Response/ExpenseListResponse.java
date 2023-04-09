package com.ood.OODPro.Payload.Response;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ExpenseListResponse {

    private Integer id;
    private String expenseName;
    private Float expensePrice;



    private Date expenseDate;

    private String category;

    private String description;

    public ExpenseListResponse(Integer id, String expenseName,Float expensePrice, Date expenseDate, String category,String description) {
        this.id = id;
        this.expenseName = expenseName;
        this.expensePrice = expensePrice;
        this.expenseDate = expenseDate;
        this.category = category;
        this.description = description;
    }
}
