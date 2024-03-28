package com.example.userservice.controller;





import com.example.userservice.entity.Utilisateur;
import com.example.userservice.service.userService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class userController {

    private final userService userService;

    @Autowired
    public userController(userService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Utilisateur> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Utilisateur getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public Utilisateur createUser(@RequestBody Utilisateur user){
        return userService.createUser(user);
    }

    @PutMapping
    public Utilisateur updateUser(@PathVariable Long id,@RequestBody Utilisateur user){
        Utilisateur existingUser = getUserById(id);
        if (existingUser != null){
            return userService.updateUser(id, user);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }





}

    

