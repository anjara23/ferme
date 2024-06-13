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
public class DiagAniDTO {
   private Integer achat;
   private Integer vendu;
   private Integer deces;
   private Integer nouv;
}

