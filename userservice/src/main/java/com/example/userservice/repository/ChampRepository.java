package com.example.userservice.repository;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long > {
    @Query("select champ from Champ champ where champ.formulaire=?1")
    List<Champ> findChampsByForm(Formulaire form);

    @Query("select champ from Champ champ where champ.formulaire.idForm=?1")
    List<Champ> findAllChampsByIdForm(Long idForm);

}
