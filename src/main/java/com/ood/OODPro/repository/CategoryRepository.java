package com.ood.OODPro.repository;

import com.ood.OODPro.Models.CategoryEntity;
import com.ood.OODPro.Models.ExpenseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
//    Optional<CategoryEntity> findAllId();

}
