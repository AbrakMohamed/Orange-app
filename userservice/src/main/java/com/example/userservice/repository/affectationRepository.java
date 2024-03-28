package com.example.userservice.repository;

import com.example.userservice.entity.affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface affectationRepository extends JpaRepository<affectation, Long> {
}
