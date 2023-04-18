package com.ood.OODPro.Payload.Request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AddBudget {

    @NotBlank
    private String emailId;


    private Float budget;

}
