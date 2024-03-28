package com.example.userservice.controller;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;
import com.example.userservice.entity.Reponse;
import com.example.userservice.service.champService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/champs")
public class ChampController {

    @Autowired
    private champService champService;

    @GetMapping
    public List<Champ> getAllChamps(){
        return champService.getAllChamps();
    }

    @GetMapping("/{id}")
    public Champ getChampById(@PathVariable("id")Long id){
        return champService.getChampById(id);
    }

    @PostMapping
    public Champ createChamp(Champ champ){
        return champService.createChamp(champ);
    }

    @PutMapping("/{id}")
    public Champ updateChamp(@PathVariable Long id,@RequestBody Champ champ){
        Champ existingChamp = getChampById(id);
        if (existingChamp != null){
            return champService.updateChamp(id,champ);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteChamp(@PathVariable Long id){
        champService.deleteChamp(id);
    }

    @DeleteMapping
    public void deleteAllChamps(){
        champService.deleteAllChamps();
    }

    @GetMapping("/formulaire/{idForm}")
    public List<Champ> findAllChampByIdForm(@PathVariable Long idForm){
        return champService.findAllChampsByIdForm(idForm);
    }
}