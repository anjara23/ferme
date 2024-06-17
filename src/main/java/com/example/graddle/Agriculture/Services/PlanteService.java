package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.PlanteEntity;
import com.example.graddle.Agriculture.Payload.PlanteRequest;
import com.example.graddle.Agriculture.Repository.PlanteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanteService {

    private final PlanteRepository planteRepository;


    public void addPlante(PlanteRequest planteRequest) {
        try {
            PlanteEntity plante = new PlanteEntity();

            plante.setType_plante(planteRequest.getType_plante());
            plante.setVariete(planteRequest.getVariete());
            plante.setDescription(planteRequest.getDescription());
            plante.setNbr_plante(planteRequest.getNbr_plante());

            planteRepository.save(plante);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add plante", e);
        }
    }

    public void updatePlante(Integer id_plante, PlanteRequest planteRequest) {
        try {
            Optional<PlanteEntity> p = planteRepository.findById(id_plante);
            if (!p.isPresent()) {
                throw new EntityNotFoundException("PlanteEntity with id " + id_plante + " not found");
            }

            PlanteEntity plante = p.get();

            if(planteRequest.getType_plante() == null){
                plante.setType_plante(plante.getType_plante());
            }
            if (planteRequest.getVariete() == null){
                plante.setVariete(plante.getVariete());
            }
            if(planteRequest.getDescription() == null){
                plante.setDescription(plante.getDescription());
            }
            if(planteRequest.getNbr_plante() == null){
                plante.setNbr_plante(plante.getNbr_plante());
            }

            plante.setType_plante(planteRequest.getType_plante());
            plante.setVariete(planteRequest.getVariete());
            plante.setDescription(planteRequest.getDescription());
            plante.setNbr_plante(planteRequest.getNbr_plante());

            planteRepository.save(plante);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update plante with id " + id_plante, e);
        }
    }

    public void updateNbP(Integer id_plante, Double x) {
        try {
            Optional<PlanteEntity> p = planteRepository.findById(id_plante);
            if (!p.isPresent()) {
                throw new EntityNotFoundException("PlanteEntity with id " + id_plante + " not found");
            }

            PlanteEntity plante = p.get();

            Double nbr = plante.getNbr_plante();
            Double nbr_plante = nbr - x;

            if (nbr_plante <= 0) {
                nbr_plante = 0.0; // Si nbr_plante est négatif, le mettre à 0
            }

            plante.setNbr_plante(nbr_plante);
            planteRepository.save(plante);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update nbr_plante for plante with id " + id_plante, e);
        }
    }

    public List<PlanteEntity> getAllPlante() {
        try {
            return planteRepository.getAllPlante();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all plante entities", e);
        }
    }

    public List<PlanteEntity> getByTypePlante(String type_plante) {
        try {
            return planteRepository.getByTypePlante(type_plante);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch plante entities by type_plante: " + type_plante, e);
        }
    }

    public List<PlanteEntity> getByVariete(String variete) {
        try {
            return planteRepository.getByVariete(variete);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch plante entities by variete: " + variete, e);
        }
    }

    public void deletePlante(Integer id_plante) {
        try {
            Optional<PlanteEntity> p = planteRepository.findById(id_plante);
            if (!p.isPresent()) {
                throw new EntityNotFoundException("PlanteEntity with id " + id_plante + " not found");
            }

            PlanteEntity plante = p.get();

            planteRepository.delete(plante);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete plante with id " + id_plante, e);
        }
    }

    public List<Object[]> getIdType() {
        try {
            return planteRepository.getIdType();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch id and type from plante entities", e);
        }
    }

}
