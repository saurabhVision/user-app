package com.intellect.test.Assignmentdemo.controller;

import com.intellect.test.Assignmentdemo.exception.RecordNotFoundException;
import com.intellect.test.Assignmentdemo.model.UserEntity;
import com.intellect.test.Assignmentdemo.repository.UserRepository;
import com.intellect.test.Assignmentdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/alluser")
    public ResponseEntity<List<UserEntity>> getAllusers(){
        List<UserEntity> list = service.getAllUsers();
        return new ResponseEntity<List<UserEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/createuser")
    public ResponseEntity<UserEntity> createOrUpdateUser(UserEntity user, HttpServletResponse response)
            throws RecordNotFoundException {
        UserEntity updated = null;
            updated = service.createOrUpdateUser(user);
        return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getcurrentmonthbirthday")
    public ResponseEntity<List<UserEntity>> getCurrentMonthBirthdate(){
        List<UserEntity> list = service.getCurrentBirthDate();
        return new ResponseEntity<List<UserEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<UserEntity> retrieveid(@PathVariable long id, UserEntity user) throws RecordNotFoundException {
        UserEntity updated = null;
        updated = service.updateUser(user);
        return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteUserById(id);
        return HttpStatus.FORBIDDEN;
    }


}
