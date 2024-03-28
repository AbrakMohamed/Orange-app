package com.example.userservice.controller;

import com.example.userservice.entity.Tache;
import com.example.userservice.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    private TacheService tacheService;

    @Autowired
    public TacheController(TacheService tacheService){
        this.tacheService = tacheService;
    }

    @GetMapping
    public List<Tache> getAllTaches(){
        return tacheService.getAllTaches();
    }

    @GetMapping("/{id}")
    public Tache getTacheById(@PathVariable Long id){
        return tacheService.getTacheById(id);
    }

    @PostMapping
    public Tache createTache(@RequestBody Tache tache){
        return tacheService.createTache(tache);
    }

    @PutMapping("/{id}")
    public Tache updateTache(@PathVariable Long id,@RequestBody Tache tache){
        Tache existingTache = getTacheById(id);
        if (existingTache != null){
            return tacheService.updateTache(id, tache);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTache(@PathVariable Long id){
        tacheService.DeleteTache(id);
    }
}
