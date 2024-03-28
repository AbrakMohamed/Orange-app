package com.example.userservice.entity;

import com.example.userservice.securite.entities.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "utilisateur")
public class Utilisateur {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomUtilisateur;

    private String motDePasse;
    private String nom;
    private String prenom;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    // Getters and setters, constructors
}

