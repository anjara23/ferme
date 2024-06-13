package com.example.graddle.Elevage.DTO;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDTO {
    private Integer id_produit;
    private String type_produit;
    private Double quantite;
    private Integer qualite;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_prod;

    private String especef;
}
