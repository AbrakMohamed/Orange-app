package com.example.userservice.service.impl;

import com.example.userservice.entity.Tache;
import com.example.userservice.repository.TacheRepository;
import com.example.userservice.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheServiceImpl implements TacheService {

    private final TacheRepository tacheRepository;

    @Autowired
    public TacheServiceImpl(TacheRepository tacheRepository){
        this.tacheRepository = tacheRepository;
    }

    @Override
    public List<Tache> getAllTaches(){
        return tacheRepository.findAll();
    }

    @Override
    public Tache getTacheById(Long id){
        return tacheRepository.findById(id).orElse(null);
    }

    @Override
    public Tache createTache(Tache tache){
        return tacheRepository.save(tache);
    }

    @Override
    public Tache updateTache(Long id, Tache tache){
        Tache existingTache = getTacheById(id);
        if (existingTache != null){
            return tacheRepository.save(tache);
        }
        return null;
    }
    @Override
    public void DeleteTache(Long id){
        tacheRepository.deleteById(id);

    }
}
