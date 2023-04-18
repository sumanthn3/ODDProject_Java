package com.ood.OODPro.repository;

import com.ood.OODPro.Models.BudgetEntity;
import com.ood.OODPro.Models.ExpenseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface BudgetRepository extends CrudRepository<BudgetEntity,Integer> {
    Optional<BudgetEntity> findOneByEmailIdIgnoreCase(String emailId);

    Optional<BudgetEntity> findByEmailId(String emailId);


}
