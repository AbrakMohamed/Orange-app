package com.example.userservice.service;

import com.example.userservice.entity.Tache;

import java.util.List;

public interface TacheService {

    List<Tache> getAllTaches();

    Tache getTacheById(Long id);

    Tache createTache(Tache tache);

    Tache updateTache(Long id, Tache tache);

    void DeleteTache(Long id);
}
