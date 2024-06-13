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
public class CalendrierDTO {
    private Integer id_calendrier;
    private Integer id_plante;
    private String activite;
    private Date date_debut;
    private Date date_fin;
    private String description;

}
