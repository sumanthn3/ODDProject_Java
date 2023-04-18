package com.ood.OODPro.Payload.Response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BudgetReponse {

    private Integer id;
    private String emailId;
    private Float budget;

    public BudgetReponse(Integer id, String emailId,Float budget) {
        this.id = id;
        this.emailId = emailId;
        this.budget=budget;
    }
}
