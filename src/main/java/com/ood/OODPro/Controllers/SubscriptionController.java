package com.ood.OODPro.Controllers;

import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Models.UserSubscriptionsEntity;
import com.ood.OODPro.Payload.Request.AddSubscription;
import com.ood.OODPro.Payload.Request.SignupRequest;
import com.ood.OODPro.Payload.Request.UpdateSubscription;
import com.ood.OODPro.Payload.Response.NewSubscriptionResponse;
import com.ood.OODPro.Payload.Response.SignupResponse;
import com.ood.OODPro.Payload.Response.SubscriptionResponse;
import com.ood.OODPro.Payload.Response.UserResponse;
import com.ood.OODPro.Utils.JwtTokenUtil;
//import com.ood.OODPro.repository.UserSubscriptionsEntity;
import com.ood.OODPro.repository.SubscriptionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin(origins = "http://localhost:8101")
public class SubscriptionController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired(required = false)
    SubscriptionRespository subscriptionRespository;
    ;

    @Autowired
    JwtTokenUtil jwtUtils;

    @GetMapping("/getSubscriptions")
    @CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        System.out.println("triggered get subscriptions api"+request.getCookies());

        String id = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));

        System.out.println("id: " + id);
        Iterable<UserSubscriptionsEntity> userSubscriptionsEntity = subscriptionRespository.findAllByEmailId(id);

        List<SubscriptionResponse> subscriptionResponses = new ArrayList<>();
        for (UserSubscriptionsEntity entity : userSubscriptionsEntity) {
            subscriptionResponses.add(new SubscriptionResponse(
                    entity.getId(),
                    entity.getSubscriptionName(),
                    entity.getSubscriptionPrice(),
                    entity.getBillingCycle(),
                    entity.getBillingDate(),
                    entity.getSendReminder(),
                    entity.getNote()
            ));
        }
        return ResponseEntity.ok().body(subscriptionResponses);


    }
    @PostMapping("/newSubscription")
    @CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
    public ResponseEntity<?> newSubscription(@Valid @RequestBody AddSubscription addSubscription) {
        System.out.println("triggered new subscription api");

        UserSubscriptionsEntity subscription = new UserSubscriptionsEntity();
        subscription.setEmailId(addSubscription.getEmailId());
        subscription.setSubscriptionName(addSubscription.getSubscriptionName());
        subscription.setSubscriptionName(addSubscription.getSubscriptionName());
        subscription.setSubscriptionPrice(addSubscription.getSubscriptionPrice());
        subscription.setBillingCycle(addSubscription.getBillingCycle());
        subscription.setBillingDate(addSubscription.getBillingDate());
        subscription.setSendReminder(addSubscription.getSendReminder());
        subscription.setNote(addSubscription.getNote());
        subscriptionRespository.save(subscription);

        return ResponseEntity.ok(new NewSubscriptionResponse("Subscription Added successfully!"));


    }
    @PutMapping("/updateSubscription")
    @CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
    public ResponseEntity<?> updateSubscription(@Valid @RequestBody UpdateSubscription updateSubscription) {
        System.out.println("triggered update subscription api");

        Optional<UserSubscriptionsEntity> optionalSubscription = subscriptionRespository.findById(Integer.valueOf(updateSubscription.getId()));

        if (optionalSubscription.isPresent()) {
            UserSubscriptionsEntity subscription = optionalSubscription.get();
            subscription.setSubscriptionName(updateSubscription.getSubscriptionName());
            subscription.setSubscriptionPrice(updateSubscription.getSubscriptionPrice());
            subscription.setBillingCycle(updateSubscription.getBillingCycle());
            subscription.setBillingDate(updateSubscription.getBillingDate());
            subscription.setSendReminder(updateSubscription.getSendReminder());
            subscription.setNote(updateSubscription.getNote());
            subscriptionRespository.save(subscription);

            return ResponseEntity.ok(new NewSubscriptionResponse("Subscription Updated successfully!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteSubscription/{id}")
    @CrossOrigin(origins = "http://localhost:8101", allowCredentials = "true")
    public ResponseEntity<?> deleteSubscription(@PathVariable Integer id) {
        System.out.println("triggered delete subscription api");

        Optional<UserSubscriptionsEntity> optionalSubscription = subscriptionRespository.findById(id);

        if (optionalSubscription.isPresent()) {
            UserSubscriptionsEntity subscription = optionalSubscription.get();
            subscriptionRespository.delete(subscription);

            return ResponseEntity.ok(new NewSubscriptionResponse("Subscription Deleted successfully!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}