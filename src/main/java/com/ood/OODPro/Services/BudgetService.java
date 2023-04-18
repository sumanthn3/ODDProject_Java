package com.ood.OODPro.Services;


import com.ood.OODPro.repository.BudgetRepository;
import com.ood.OODPro.repository.SubscriptionRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BudgetService {

    @Autowired(required = false)
    BudgetRepository budgetRepository;


}
