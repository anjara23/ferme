package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.CultureEntity;
import com.example.graddle.Agriculture.Entities.Stade_pEntity;
import com.example.graddle.Agriculture.Payload.Stade_pRequest;
import com.example.graddle.Agriculture.Repository.Stade_pRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Stade_pService {

    private final Stade_pRepository stade_pRepository;


    public void addStade(Stade_pRequest stade_pRequest) {
        try {
            Stade_pEntity stade = new Stade_pEntity();

            Integer id_cultiver = stade_pRequest.getId_cultiver();

            CultureEntity cult = new CultureEntity();
            cult.setId_cultiver(id_cultiver);

            stade.setCulture(cult);
            stade.setEtape(stade_pRequest.getEtape());
            stade.setDate_debut(stade_pRequest.getDate_debut());
            stade.setDate_fin(stade_pRequest.getDate_fin());
            stade.setBesoin_e(stade_pRequest.getBesoin_e());

            stade_pRepository.save(stade);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add stade_pEntity", e);
        }
    }


    public void updateStade(Integer id_stade, Stade_pRequest stade_pRequest) {
        try {
            Optional<Stade_pEntity> st = stade_pRepository.findById(id_stade);
            if (!st.isPresent()) {
                throw new EntityNotFoundException("Stade_PEntity with id " + id_stade + " not found");
            }

            Stade_pEntity stade = st.get();
            stade.setEtape(stade_pRequest.getEtape());
            stade.setDate_debut(stade_pRequest.getDate_debut());
            stade.setDate_fin(stade_pRequest.getDate_fin());
            stade.setBesoin_e(stade_pRequest.getBesoin_e());

            stade_pRepository.save(stade);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update stade_pEntity with id " + id_stade, e);
        }
    }

    public List<Object[]> diagSt(Integer id_cultiver) {
        try {
            return stade_pRepository.diagSt(id_cultiver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch diagnostic data for id_cultiver: " + id_cultiver, e);
        }
    }

    public List<Object[]> getAll() {
        try {
            return stade_pRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all data from Stade_pEntity", e);
        }
    }

    public void deleteStade(Integer id_cultiver) {
        try {
            Optional<Stade_pEntity> stade = stade_pRepository.getByIdcul(id_cultiver);
            if (!stade.isPresent()) {
                throw new EntityNotFoundException("Stade_pEntity with id " + id_cultiver + " not found");
            }
            Stade_pEntity sta = stade.get();
            stade_pRepository.delete(sta);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Stade_pEntity with id " + id_cultiver, e);
        }
    }

}
