package com.example.userservice.securite.repositories;

import com.example.userservice.securite.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface roleRepository extends JpaRepository<Role, Long> {


}
