package com.cehl.avaapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cehl.avaapplication.model.UserRoles;

public interface RolesRepository extends JpaRepository<UserRoles, Long>{
    
}
