package com.example.userservice.service.impl;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;
import com.example.userservice.repository.ChampRepository;
import com.example.userservice.service.champService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ChampServiceImpl implements champService {

    @Autowired
    ChampRepository champRepository;
    @Override
    public Champ createChamp(Champ champ) {
        return champRepository.save(champ);
    }



    @Override
    public Champ updateChamp(Long id,Champ champ) {
        Champ existingChamp = getChampById(id);
        if (existingChamp != null){
            return champRepository.save(existingChamp);
        }
        return null;

    }

    @Override
    public void deleteChamp(Long idChamp) {
        champRepository.deleteById(idChamp);
    }
    @Override
    public void deleteAllChamps() {
        champRepository.deleteAll();
    }

    @Override
    public Champ getChampById(Long idChamp) {
        return champRepository.findById(idChamp).orElse(null);
    }

    @Override
    public List<Champ> getAllChamps() {
        return champRepository.findAll();
    }

    @Override
    public List<Champ> findAllChampsByForm(Formulaire formulaire){
        return champRepository.findChampsByForm(formulaire);
    }

    @Override
    public List<Champ> findAllChampsByIdForm(Long idForm) {
        return champRepository.findAllChampsByIdForm(idForm);
    }


}
