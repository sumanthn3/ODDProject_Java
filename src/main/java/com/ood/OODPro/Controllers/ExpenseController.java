package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.ExpenseEntity;
import com.ood.OODPro.Models.UserSubscriptionsEntity;
import com.ood.OODPro.Payload.Request.AddExpense;
import com.ood.OODPro.Payload.Request.AddSubscription;
import com.ood.OODPro.Payload.Request.UpdateExpense;
import com.ood.OODPro.Payload.Request.UpdateSubscription;
import com.ood.OODPro.Payload.Response.ExpenseListResponse;
import com.ood.OODPro.Payload.Response.NewSubscriptionResponse;
import com.ood.OODPro.Payload.Response.SubscriptionResponse;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.repository.ExpenseRepository;
import com.ood.OODPro.repository.SubscriptionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
public class ExpenseController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired(required = false)
    ExpenseRepository expenseRepository;
    ;

    @Autowired
    JwtTokenUtil jwtUtils;

    @GetMapping("/getExpenses")
    @CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        System.out.println("triggered get expenses api"+request.getCookies());

        String id = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));

        System.out.println("id: " + id);
        Iterable<ExpenseEntity> expenseEntities = expenseRepository.findAllByEmailId(id);
        List<ExpenseListResponse> expenseListResponses = new ArrayList<>();
        for (ExpenseEntity expenseEntity : expenseEntities) {
            expenseListResponses.add(new ExpenseListResponse(
                    expenseEntity.getId(),
                    expenseEntity.getExpenseName(),
                    expenseEntity.getExpensePrice(),
                    expenseEntity.getExpenseDate(),
                    expenseEntity.getCategory(),
                    expenseEntity.getDescription()
            ));
        }
        return ResponseEntity.ok().body(expenseListResponses);


    }
    @PostMapping("/newExpense")
    @CrossOrigin(origins = "http://localhost:8101",allowCredentials = "true")
    public ResponseEntity<?> newSubscription(@Valid @RequestBody AddExpense addExpense) {
        System.out.println("triggered new expense api");

//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(addSubscription.getEmailId(), addSubscription.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        System.out.println("triggered signup api with body: " + AddSubscriptionResponse.toString());

        // Create new user's subscription
        ExpenseEntity expense = new ExpenseEntity();
        expense.setEmailId(addExpense.getEmailId());
        expense.setExpenseName(addExpense.getExpenseName());
        expense.setExpensePrice(addExpense.getExpensePrice());
        expense.setExpenseDate(addExpense.getExpenseDate());
        expense.setCategory(addExpense.getCategory());
        expense.setDescription(addExpense.getDescription());
        expenseRepository.save(expense);

        return ResponseEntity.ok(new NewSubscriptionResponse("expense Added successfully!"));


    }
    @PutMapping("/updateExpense")
    @CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
    public ResponseEntity<?> updateExpense(@Valid @RequestBody UpdateExpense updateExpense) {
        System.out.println("triggered update expense api");

        Optional<ExpenseEntity> optionalExpense = expenseRepository.findById(updateExpense.getId());

        if (optionalExpense.isPresent()) {
            ExpenseEntity expense = optionalExpense.get();
            expense.setExpenseName(updateExpense.getExpenseName());
            expense.setExpensePrice(updateExpense.getExpensePrice());
            expense.setExpenseDate(updateExpense.getExpenseDate());
            expense.setCategory(updateExpense.getCategory());
            expense.setDescription(updateExpense.getDescription());
            expenseRepository.save(expense);

            return ResponseEntity.ok(new NewSubscriptionResponse("Expense Updated successfully!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}