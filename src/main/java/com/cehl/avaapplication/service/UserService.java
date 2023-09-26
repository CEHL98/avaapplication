package com.cehl.avaapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cehl.avaapplication.model.UserModel;
import com.cehl.avaapplication.model.UserRoles;
import com.cehl.avaapplication.repository.RolesRepository;
import com.cehl.avaapplication.repository.UserRepository;

@Service
public class UserService {

    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;

    public UserService(RolesRepository rolesRepository, UserRepository userRepository) {
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> createTeacherStudent(UserModel userModel, Long roleId) {

        Optional<UserRoles> userRoles = rolesRepository.findById(roleId);
        if (userRoles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Unable to assign a role to this user");
        }
        UserRoles userRoles2 = userRoles.get();
        userRoles2.getUsers().add(userModel);
        userModel.setUserRole(userRoles2);

        rolesRepository.save(userRoles2);
        userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);

    }

    public ResponseEntity<Object> searchUserById(Long userId) {
        Optional<UserModel> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    public ResponseEntity<Object> listAlluser (){
        List<UserModel> listUser = userRepository.findAll();
        if(listUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no users");
        }

        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    
    }

    public ResponseEntity<Object> deleteTeacherStudent (Long id){
        Optional<UserModel> userModel = userRepository.findById(id);
        if(userModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Optional<UserRoles> userRoles = rolesRepository.findById(userModel.get().getUserRole().getRoleId());
        if(userRoles.isPresent()){
            userRoles.get().getUsers().remove(userModel.get());
        }
        userRepository.delete(userModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("User has been deleted");
    }  
    
    public ResponseEntity<Object> updateTeacherStudent (Long userId, UserModel userNew){
        Optional<UserModel> userModel = userRepository.findById(userId);
        if(userModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        UserModel user = userModel.get();
        BeanUtils.copyProperties(userNew, user, "email","userRole","userId");
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));

    }

}
