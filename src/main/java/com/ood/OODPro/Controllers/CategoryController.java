package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.CategoryEntity;
import com.ood.OODPro.Models.ExpenseEntity;
import com.ood.OODPro.Payload.Request.AddExpense;
import com.ood.OODPro.Payload.Response.CategoryResponse;
import com.ood.OODPro.Payload.Response.ExpenseListResponse;
import com.ood.OODPro.Payload.Response.NewSubscriptionResponse;
import com.ood.OODPro.Utils.JwtTokenUtil;
import com.ood.OODPro.repository.CategoryRepository;
import com.ood.OODPro.repository.ExpenseRepository;
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
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:8100")
public class CategoryController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired(required = false)
    CategoryRepository categoryRepository;


    @Autowired
    JwtTokenUtil jwtUtils;

    @GetMapping("/getAllCategories")
    @CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        System.out.println("triggered  getAllCategories api"+request.getCookies());

        String id = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));

        System.out.println("id: " + id);

        Iterable<CategoryEntity> categoryEntity = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (CategoryEntity category : categoryEntity) {
            categoryResponses.add(new CategoryResponse(category.getId(), category.getCategoryName()));
        }
        return ResponseEntity.ok().body(categoryResponses);


    }


}