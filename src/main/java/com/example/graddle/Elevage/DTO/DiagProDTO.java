package com.example.graddle.Elevage.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiagProDTO {
    private Double quantite;
    private Integer qualite;
    private Date date_prod;
}
