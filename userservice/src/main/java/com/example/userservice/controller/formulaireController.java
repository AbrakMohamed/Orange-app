package com.example.userservice.controller;

import com.example.userservice.entity.Formulaire;
import com.example.userservice.service.formulaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formulaires")
public class formulaireController {

    private final formulaireService formulaireService;

    @Autowired
    public formulaireController( formulaireService formulaireService){
        this.formulaireService=formulaireService;
    }

    @GetMapping
    public List<Formulaire> getAllFormulaires() {
       return formulaireService.getAllFormulaires();
    }

    @GetMapping("/{id}")
    public Formulaire getFormulaireById(@PathVariable Long id){
        return formulaireService.getFormulaireById(id);
    }

    @PostMapping
    public Formulaire createFormulaire(@RequestBody  Formulaire formulaire){
       return formulaireService.createFormulaire(formulaire);
    }


    @PutMapping("/{id}")
    public Formulaire updateFormulaire(@PathVariable Long id,@RequestBody Formulaire formulaire){
        Formulaire existingFormulaire = getFormulaireById(id);
        if (existingFormulaire != null) {
            formulaire.setIdForm(id);
            Formulaire updatedFormulaire = formulaireService.updateFormulaire(id,formulaire);
            return updatedFormulaire;
        }
            return null;
    }

    @DeleteMapping("/{id}")
    public void deleteFormulaire(@PathVariable Long id) {
        formulaireService.deleteFormulaire(id);
    }





}
