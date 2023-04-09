package com.ood.OODPro.Models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
@Entity
@Table(name = "users_expenses",uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
@Data
public class ExpenseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "expense_name")
    private String expenseName;

    @Column(name = "expense_price")
    private Float expensePrice;

    @Column(name = "expense_date")
    private Date expenseDate;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
