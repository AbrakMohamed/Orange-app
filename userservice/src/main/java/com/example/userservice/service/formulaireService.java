package com.example.userservice.service;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;

import java.util.List;

public interface formulaireService {

        List<Formulaire> getAllFormulaires();

        Formulaire getFormulaireById(Long id);

        Formulaire createFormulaire(Formulaire formulaire);

        Formulaire updateFormulaire(Long id, Formulaire updatedFormulaire);

        void deleteFormulaire(Long id);
}
