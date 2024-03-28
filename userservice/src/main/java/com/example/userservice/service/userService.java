package com.example.userservice.service;


import com.example.userservice.entity.Utilisateur;

import java.util.List;

public interface userService {

    List<Utilisateur> getAllUsers();

    Utilisateur getUserById(Long id);

    Utilisateur createUser(Utilisateur user);

    Utilisateur updateUser(Long id, Utilisateur updatedUser);

    void deleteUser(Long id);
}

