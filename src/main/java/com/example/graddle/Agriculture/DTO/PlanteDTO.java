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
public class PlanteDTO {
    private Integer id_plante;
    private String type_plante;
    private String variete;
    private String description;
    private Double nbr_plante;
}
