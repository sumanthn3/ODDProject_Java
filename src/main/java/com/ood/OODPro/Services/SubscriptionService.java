package com.ood.OODPro.Services;

import com.ood.OODPro.Constants.Constants;
import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Models.UserSubscriptionsEntity;
import com.ood.OODPro.exception.ApiException;
import com.ood.OODPro.repository.SubscriptionRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SubscriptionService {

    @Autowired(required = false)
    SubscriptionRespository subscriptionRespository;

//    public UserSubscriptionsEntity getUserEntityWithEmail(String email) throws ApiException {
//        log.info("getUserEntityWithEmail --> {}", email);
//        Iterable<UserSubscriptionsEntity> adminEntityOptional = subscriptionRespository.findAllByEmailId(email);
//        if (!adminEntityOptional.iterator().hasNext()) {
//            log.error(Constants.USER_DOES_NOT_EXISTS);
//            throw new ApiException(Constants.USER_DOES_NOT_EXISTS, HttpStatus.BAD_REQUEST);
//        }
//        return adminEntityOptional.get();
//    }
}