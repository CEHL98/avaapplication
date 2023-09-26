package com.cehl.avaapplication.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cehl.avaapplication.model.UserModel;

import com.cehl.avaapplication.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add/user/{id}",consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Object> createrUser (@PathVariable(value = "id")Long roleId,
    @RequestBody UserModel user){
        return userService.createTeacherStudent(user, roleId);
    }
   @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> searchUser (@PathVariable(value = "id") Long id){
       return userService.searchUserById(id);
    }

     @GetMapping (value = "list/user")
    public ResponseEntity<Object> listAllUsers (){
        return userService.listAlluser();
    }

     @DeleteMapping (value = "/delete/user/{id}")
    public ResponseEntity<Object> deleteUser (@PathVariable (value = "id")Long id){
         return userService.deleteTeacherStudent(id);
    }

    @PutMapping(value = "/edit/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id")Long id,
    @RequestBody UserModel user){
        return userService.updateTeacherStudent(id, user);
    } 

    
}
