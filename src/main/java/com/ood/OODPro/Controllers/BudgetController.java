package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.BudgetEntity;
import com.ood.OODPro.Models.ExpenseEntity;
import com.ood.OODPro.Payload.Request.AddBudget;
import com.ood.OODPro.Payload.Request.AddExpense;
import com.ood.OODPro.Payload.Response.BudgetReponse;
import com.ood.OODPro.Payload.Response.ExpenseListResponse;
import com.ood.OODPro.Payload.Response.NewSubscriptionResponse;
import com.ood.OODPro.Payload.Response.UserResponse;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.repository.BudgetRepository;
import com.ood.OODPro.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
public class BudgetController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired(required = false)
    BudgetRepository budgetRepository;
    ;

    @Autowired
    JwtTokenUtil jwtUtils;

    @GetMapping("/getBudget")
    @CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        System.out.println("triggered get budget api"+request.getCookies());

        String id = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));

        System.out.println("id: " + id);
        Optional<BudgetEntity> budgetEntity = budgetRepository.findByEmailId(id);
        if (budgetEntity.isPresent()) {
            BudgetEntity budget = budgetEntity.get();
            BudgetReponse response = new BudgetReponse(budget.getId(), budget.getEmailId(), budget.getBudget());
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/newBudget")
    @CrossOrigin(origins = "http://localhost:8100",allowCredentials = "true")
    public ResponseEntity<?> newSubscription(@Valid @RequestBody AddBudget addBudget) {
        System.out.println("triggered new budget api");


        // Create new user's budget
        BudgetEntity budget = new BudgetEntity();
        budget.setEmailId(addBudget.getEmailId());
        budget.setBudget(addBudget.getBudget());
        budgetRepository.save(budget);

        return ResponseEntity.ok(new NewSubscriptionResponse("Budget Added successfully!"));


    }
    @PutMapping("/updateBudget")
    @CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
    public ResponseEntity<?> updateExpense(@Valid @RequestBody AddBudget addBudget) {
        System.out.println("triggered update budget api");

        Optional<BudgetEntity> optionalBudget = budgetRepository.findByEmailId(addBudget.getEmailId());

        if (optionalBudget.isPresent()) {
            BudgetEntity budget = optionalBudget.get();
            budget.setEmailId(addBudget.getEmailId());
            budget.setBudget(addBudget.getBudget());
            budgetRepository.save(budget);

            return ResponseEntity.ok(new NewSubscriptionResponse("Budget Updated successfully!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}