package com.cehl.avaapplication.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserModel implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private long userId;

    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false,name = "lastName")
    private String lastName;

    @Column(nullable = false, name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRoles userRole;
    
    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRoles", 
    joinColumns = @JoinColumn(name ="userId"),
    inverseJoinColumns = @JoinColumn(name="roleId"))
    Set<UserRoles> userRoles = new HashSet<>(); */
    
}
