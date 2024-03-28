package com.example.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="champ")
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChamp;
    private String nomChamp;
    private String typeChamp;
    private String question;


    @ManyToOne
            //(optional = false)
    @JoinColumn(name = "form_id")
    private Formulaire formulaire;

    @OneToMany(mappedBy = "champs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reponse> reponses = new ArrayList<>();

   public Champ(String nomChamp, String typeChamp, List<Reponse> reponses, String question) {
        this.nomChamp = nomChamp;
        this.typeChamp = typeChamp;
        this.reponses = reponses;
        this.question = question;

    }

    public Champ(String nomChamp, String typeChamp, String question,Formulaire formulaire) {
        this.nomChamp = nomChamp;
        this.typeChamp = typeChamp;
        this.question = question;
        this.formulaire=formulaire;
    }


}

