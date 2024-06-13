package com.example.graddle.Agriculture.Repository;

import com.example.graddle.Agriculture.Entities.CultureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CultureRepository extends JpaRepository<CultureEntity, Integer> {

    @Query(value = "SELECT id_cultiver, code_parcelle, id_plante, date_plantation, date_production, date_recolte, produit_kg, nb_planter, surface_c, resultat_c FROM public.culture", nativeQuery = true)
    List<CultureEntity> getAllC();

    @Query(value = "SELECT c.* FROM public.culture c JOIN public.plante p ON c.id_plante = p.id_plante WHERE p.variete = :variete", nativeQuery = true)
    List<CultureEntity> getByVariete(String variete);

    @Query(value = "SELECT * FROM public.culture  WHERE date_recolte IS NOT NULL  AND produit_kg IS NOT NULL ", nativeQuery = true)
    List<CultureEntity> getFinished();

    @Query(value = "SELECT * FROM public.culture WHERE EXTRACT(MONTH FROM culture.date_plantation) = :mois", nativeQuery = true)
    List<CultureEntity> getByDate(Integer mois);
}
