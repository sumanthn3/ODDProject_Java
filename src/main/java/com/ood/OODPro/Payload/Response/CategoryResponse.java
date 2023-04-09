package com.ood.OODPro.Payload.Response;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CategoryResponse {

    private Integer id;
    private String categoryName;

    public CategoryResponse(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
