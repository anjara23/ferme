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
public class ParcelleDTO {

    private Integer code_parcelle;
    private Double latitude;
    private Double longitude;
    private String type_sol;
    private String type_culture_avant;
    private Double surface;
}
