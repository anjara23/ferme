package com.example.graddle.Elevage.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Entity
@Table(name = "sante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sante;

    //declarer clé etrangère
    @OneToOne
    @JoinColumn(name = "id_animal")
    private AnimalEntity id_animal;

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
    private Boolean gestation;

}
