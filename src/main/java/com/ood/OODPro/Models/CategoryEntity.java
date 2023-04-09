package com.ood.OODPro.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "categories",uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
@Data
public class CategoryEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}