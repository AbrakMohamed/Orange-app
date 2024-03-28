package com.example.userservice.service.impl;

import com.example.userservice.entity.Utilisateur;
import com.example.userservice.repository.userRepository;
import com.example.userservice.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    private final userRepository userRepository;

    @Autowired
    public userServiceImpl(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Utilisateur getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Utilisateur createUser(Utilisateur user) {
        // Ajouter la logique métier nécessaire avant d'enregistrer l'utilisateur
        return userRepository.save(user);
    }

    @Override
    public Utilisateur updateUser(Long id, Utilisateur updatedUser) {
        Utilisateur existingUser = getUserById(id);

        if (existingUser != null) {
            // Mettre à jour les propriétés de l'utilisateur existant avec celles de l'utilisateur mis à jour
            existingUser.setNom(updatedUser.getNom());
            existingUser.setPrenom(updatedUser.getPrenom());

            // Enregistrez les modifications dans la base de données
            return userRepository.save(existingUser);
        }

        return null; // Ou lancez une exception selon le comportement souhaité
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
