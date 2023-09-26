package com.cehl.avaapplication.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cehl.avaapplication.model.UserRoles;
import com.cehl.avaapplication.repository.RolesRepository;

@RestController
public class RolesController {
    
    private final RolesRepository rolesRepository;

    public RolesController(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @PostMapping(value = "/add/role",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoles> createRole (@RequestBody UserRoles userRoles){
        System.out.println(userRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesRepository.save(userRoles));
    }

    @GetMapping(value = "/list/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserRoles>> listAllRole (){
        return ResponseEntity.status(HttpStatus.OK).body(rolesRepository.findAll());
    }

    @GetMapping (value = "/role/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> searchRoleById(@PathVariable(value = "id") Long id){
        Optional<UserRoles> userRole = rolesRepository.findById(id);
        if (userRole.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userRole.get());
    }

    @DeleteMapping(value="/delete/role/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteRole (@PathVariable (value = "id")Long id){
        Optional<UserRoles> roleOptional = rolesRepository.findById(id);
        if(roleOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
        rolesRepository.delete(roleOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Role has been deleted");
    }

    @PutMapping(value="/edit/role/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateRole (@PathVariable(value = "id")Long id, @RequestBody UserRoles roles){
     Optional<UserRoles> roleOptional = rolesRepository.findById(id);
        if(roleOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
        UserRoles userRoles = roleOptional.get();
        BeanUtils.copyProperties(roles, userRoles);
        return ResponseEntity.status(HttpStatus.OK).body(rolesRepository.save(userRoles));
    }


    
}
