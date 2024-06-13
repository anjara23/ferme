package com.example.graddle.Elevage.Payload;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanteRequest {

   // private AnimalEntity id_animal;

    private boolean vaccin;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_vacc;

    private boolean vermifuge;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_verm;

    private String maladie;
    private String blessure;
    private String traitement;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date_trait;

    private Integer etat;
    private boolean gestation;
}
