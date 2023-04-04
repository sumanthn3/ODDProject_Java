package com.ood.OODPro.repository;

import com.ood.OODPro.Models.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findOneByEmailIdIgnoreCase(String emailId);

    Optional<UserEntity> findOneByEmailIdIgnoreCaseAndPhoneNumberIgnoreCase(String emailId, String phoneNumber);

    Optional<UserEntity> findOneById(long Id);
    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmailId(String emaili);

    @Query("SELECT u FROM UserEntity u WHERE u.emailId LIKE 'a%'")
    List<UserEntity> getUsersWithActiveStatusAndEmailStartsWithA();
}
