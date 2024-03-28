package com.example.userservice.repository;

import com.example.userservice.entity.ResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ResponseRepository extends JpaRepository<ResponseEntity , Long> {
}
