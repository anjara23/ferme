package com.example.graddle.Elevage.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EssentialDTO {
    private Integer id_animal;
    private String espece;
    private String nom;
    private String sexe;
    private String statut;
    private Integer etat;
}
