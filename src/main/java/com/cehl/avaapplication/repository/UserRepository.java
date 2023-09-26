package com.cehl.avaapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cehl.avaapplication.model.UserModel;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <UserModel, Long>{
 

    Optional<UserModel>findByEmail(String email);
}
