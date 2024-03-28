package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "cell")
public class CellEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCell;
    @ManyToOne(cascade = CascadeType.ALL)
    //(optional = false)
    @JoinColumn(name="idChamp")
    Champ row;
    String value;

    public CellEntity(Champ row, String value) {
        this.row = row;
        this.value =value;
}
}