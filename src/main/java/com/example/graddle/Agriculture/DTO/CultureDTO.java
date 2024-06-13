package com.example.graddle.Agriculture.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CultureDTO {

    private Integer id_cultiver;
    private Integer code_parcelle;
    private Integer id_plante;
    private Date date_plantation;
    private Date date_production;
    private Date date_recolte;
    private Double produit_kg;
    private Double nb_planter;
    private Double surface_c;
    private String resultat_c;
}
