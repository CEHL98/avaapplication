package com.cehl.avaapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cehl.avaapplication.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "role_user")
    private Role roles;

    @JsonIgnore
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserModel> users = new HashSet<>();

    /*@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "userRoles")
    private Set<UserModel> users = new HashSet<>(); */
    
}
