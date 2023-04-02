package com.ood.OODPro.Services;

import com.ood.OODPro.Constants.Constants;
import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.exception.ApiException;


import com.ood.OODPro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {



    @Autowired(required = false)
    UserRepository userRepository;

    public UserEntity getUserEntityWithEmail(String email) throws ApiException {
        log.info("getUserEntityWithEmail --> {}", email);
        Optional<UserEntity> adminEntityOptional = userRepository.findOneByEmailIdIgnoreCase(email);
        if (!adminEntityOptional.isPresent()) {
            log.error(Constants.USER_DOES_NOT_EXISTS);
            throw new ApiException(Constants.USER_DOES_NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        return adminEntityOptional.get();
    }
}
