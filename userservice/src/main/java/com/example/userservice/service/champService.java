package com.example.userservice.service;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;
import com.example.userservice.entity.Reponse;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface champService {

    Champ createChamp(Champ champ);
    Champ updateChamp (Long id, Champ champ);
    void deleteChamp(Long idChamp);
    void deleteAllChamps();
    Champ getChampById(Long idChamp);
    List<Champ> getAllChamps();
    List<Champ> findAllChampsByForm(Formulaire formulaire);
    List<Champ> findAllChampsByIdForm(Long idForm);


}
