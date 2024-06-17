package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.ParcelleEntity;
import com.example.graddle.Agriculture.Payload.ParcelleRequest;
import com.example.graddle.Agriculture.Repository.ParcelleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParcelleService {

    private final ParcelleRepository parcelleRepository;

    public  void addParcelle(ParcelleRequest parcelleRequest){

        ParcelleEntity parc = new ParcelleEntity();

        parc.setLatitude(parcelleRequest.getLatitude());
        parc.setLongitude(parcelleRequest.getLongitude());
        parc.setType_sol(parcelleRequest.getType_sol());
        parc.setType_culture_avant(parcelleRequest.getType_culture_avant());
        parc.setSurface(parcelleRequest.getSurface());

        parcelleRepository.save(parc);
    }

    public void updateParcelle(Integer code_parcelle,ParcelleRequest parcelleRequest){
        try{
        Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
        if(!pa.isPresent()){
            throw new EntityNotFoundException("ParcelleEntity with id " + code_parcelle + " not found");
        }

        ParcelleEntity parc = pa.get();
        Double latitude = parcelleRequest.getLatitude();
        Double longitude = parcelleRequest.getLongitude();
        String type_sol = parcelleRequest.getType_sol();
        String cultu = parcelleRequest.getType_culture_avant();
        Double surface = parcelleRequest.getSurface();


        if(latitude != null){
            parc.setLatitude(latitude);
        }
        if(longitude != null){
            parc.setLongitude(longitude);
        }
        if(type_sol != null){
            parc.setType_sol(type_sol);
        }
        if(cultu != null){
            parc.setType_culture_avant(cultu);
        }
        if(surface != null){
            parc.setSurface(surface);
        }

        parcelleRepository.save(parc);
    } catch (Exception e) {
        throw new RuntimeException("Failed to add parcelle", e);
    }

    }

    public void updateTypeAvant(Integer code_parcelle, String type_avant) {
        try {
            Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
            if (!pa.isPresent()) {
                throw new EntityNotFoundException("ParcelleEntity with id " + code_parcelle + " not found");
            }
            ParcelleEntity parc = pa.get();
            parc.setType_culture_avant(type_avant);

            parcelleRepository.save(parc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update type_avant for parcelle with id " + code_parcelle, e);
        }
    }

    public Boolean updateSurface(Integer code_parcelle, Double x) {
        try {
            Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
            if (!pa.isPresent()) {
                throw new EntityNotFoundException("ParcelleEntity with id " + code_parcelle + " not found");
            }

            ParcelleEntity parc = pa.get();
            Double surf = parc.getSurface();
            Double surface = surf + x;

            if (surface < 0) {
                return false;
            }

            parc.setSurface(surface);
            parcelleRepository.save(parc);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update surface for parcelle with id " + code_parcelle, e);
        }
    }

    public Double getEspaceL(Integer code_parcelle) {
        try {
            return parcelleRepository.getEspaceL(code_parcelle);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch espaceL for parcelle with id " + code_parcelle, e);
        }
    }

    public List<ParcelleEntity> getDispo() {
        try {
            return parcelleRepository.getDispo();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch available parcels", e);
        }
    }

    public List<Object[]> getPlanter() {
        try {
            return parcelleRepository.getPlanter();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch planter information", e);
        }
    }

    public List<ParcelleEntity> getAllParc() {
        try {
            return parcelleRepository.getAllParc();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all parcels", e);
        }
    }

    public List<ParcelleEntity> getByType(String type_sol) {
        try {
            return parcelleRepository.getByType(type_sol);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch parcels by type_sol: " + type_sol, e);
        }
    }

    public List<ParcelleEntity> getTypeAvant(String type_culture_avant) {
        try {
            return parcelleRepository.getTypeAvant(type_culture_avant);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch parcels by type_culture_avant: " + type_culture_avant, e);
        }
    }

    public void deleteParcelle(Integer code_parcelle) {
        try {
            Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
            if (!pa.isPresent()) {
                throw new EntityNotFoundException("ParcelleEntity with id " + code_parcelle + " not found");
            }

            ParcelleEntity parc = pa.get();
            parcelleRepository.delete(parc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete parcelle with id " + code_parcelle, e);
        }
    }

    public List<Object[]> getCodeType() {
        try {
            return parcelleRepository.getCodeType();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch code and type information for parcels", e);
        }
    }
}
