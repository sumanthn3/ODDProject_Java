package com.ood.OODPro.repository;

import com.ood.OODPro.Models.UserEntity;
import com.ood.OODPro.Models.UserSubscriptionsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRespository extends CrudRepository<UserSubscriptionsEntity,Integer> {
    Optional<UserSubscriptionsEntity> findOneByEmailIdIgnoreCase(String emailId);

    Optional<UserSubscriptionsEntity> findAllByEmailId(String emailId);

    Boolean existsByEmailId(String emaili);

    @Query("SELECT u FROM UserEntity u WHERE u.emailId LIKE 'a%'")
    List<UserEntity> getUsersWithActiveStatusAndEmailStartsWithA();
}
