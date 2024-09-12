package com.example.boot.Controller;


import com.example.boot.Entity.User;
import com.example.boot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/api/users/create
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User saveUser = userService.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/users/get/2
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getData(@PathVariable Long id){
        User user = userService.getData(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    //http://localhost:8080/api/users

    @GetMapping
    public ResponseEntity<List<User>> getAllData(){
        List<User> AllData = userService.getAllData();
        return new ResponseEntity<>(AllData, HttpStatus.OK);
    }

    //http://localhost:8080/api/users/update/2

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    //http://localhost:8080/api/users/delete/2
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User Data deleted Successfully";
    }
}
