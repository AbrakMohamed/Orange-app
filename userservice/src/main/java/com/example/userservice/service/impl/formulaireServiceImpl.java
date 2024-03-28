package com.example.userservice.service.impl;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;
import com.example.userservice.repository.FormulaireRepository;
import com.example.userservice.service.formulaireService;
import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class formulaireServiceImpl implements formulaireService {

    private FormulaireRepository formulaireRepository;

    @Autowired
    public formulaireServiceImpl(FormulaireRepository formulaireRepository) {
        this.formulaireRepository = formulaireRepository;
    }



    @Override
    public List<Formulaire> getAllFormulaires() {
        return formulaireRepository.findAll();
    }

    @Override
    public Formulaire getFormulaireById(Long id) {
        return formulaireRepository.findById(id).orElse(null);
    }

    @Override
    public Formulaire createFormulaire(Formulaire formulaire) {
        // Ajouter la logique métier nécessaire avant d'enregistrer le formulaire
        return formulaireRepository.save(formulaire);
    }

    @Override
    public Formulaire updateFormulaire(Long id, Formulaire updatedFormulaire) {
        Formulaire existingFormulaire = getFormulaireById(id);

        if (existingFormulaire != null) {
            // Mettre à jour les propriétés du formulaire existant avec celles du formulaire mis à jour
            existingFormulaire.setNomForm(updatedFormulaire.getNomForm());
            existingFormulaire.setDescForm(ClassUtil.getClassDescription(updatedFormulaire));

            // Enregistrez les modifications dans la base de données
            return formulaireRepository.save(existingFormulaire);
        }

        return null; // Ou lancez une exception selon le comportement souhaité
    }



    @Override
    public void deleteFormulaire(Long id) {
        formulaireRepository.deleteById(id);
    }
}
