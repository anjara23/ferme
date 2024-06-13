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
public class DiagDTO {
    private Integer id_stade;
    private String etape;
    private Date date_debut;
    private Date date_fin;
    private Double besoin_e;
}
