package com.ood.OODPro.repository;

import com.ood.OODPro.Models.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findOneByEmailIdIgnoreCase(String emailId);
    Optional<UserEntity> findOneById(long Id);
    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);
}
