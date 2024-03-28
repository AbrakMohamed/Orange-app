package com.example.userservice.repository;

import com.example.userservice.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface userRepository extends JpaRepository<Utilisateur, Long> {

}

