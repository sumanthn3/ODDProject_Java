package com.ood.OODPro.Payload.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AddExpense {

    @NotBlank
    private String emailId;
    @NotBlank
    private String expenseName;


    private Float expensePrice;


    private Date expenseDate;

    @NotBlank
    private String category;

    private String description;
}
