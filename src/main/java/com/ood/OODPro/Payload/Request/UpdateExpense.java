package com.ood.OODPro.Payload.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
public class UpdateExpense {


    private Integer id;
    @NotBlank
    private String expenseName;


    private Float expensePrice;


    private Date expenseDate;

    @NotBlank
    private String category;

    private String description;
}