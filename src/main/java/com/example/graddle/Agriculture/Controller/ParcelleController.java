package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.DTO.CodeTypeDTO;
import com.example.graddle.Agriculture.DTO.ParcelleDTO;
import com.example.graddle.Agriculture.Entities.ParcelleEntity;
import com.example.graddle.Agriculture.Payload.ParcelleRequest;
import com.example.graddle.Agriculture.Services.ParcelleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/agriculture/parcelle")
@RequiredArgsConstructor
public class ParcelleController {

    private final ParcelleService parcelleService;

    @PostMapping("/addParcelle")
    public void addParcelle(@RequestBody ParcelleRequest parcelleRequest){
        parcelleService.addParcelle(parcelleRequest);
    }

    @PutMapping("/updateP/{code_parcelle}")
    public void updateParcelle(@PathVariable Integer code_parcelle, @RequestBody ParcelleRequest parcelleRequest){
        parcelleService.updateParcelle(code_parcelle, parcelleRequest);
    }

    //get niova post
    @GetMapping("/getEspaceL/{code_parcelle}")
    public Double getEspaceL(@PathVariable Integer code_parcelle){
        return parcelleService.getEspaceL(code_parcelle);
    }

    @GetMapping("/getAll")
    public List<ParcelleDTO> getAllParc(){
        List<ParcelleEntity> parc = parcelleService.getAllParc();
        List<ParcelleDTO> parcelleDTOs = new ArrayList<>();
        for (ParcelleEntity parcelle : parc) {
            ParcelleDTO parcelleDTO = new ParcelleDTO();
            parcelleDTO.setCode_parcelle(parcelle.getCode_parcelle());
            parcelleDTO.setLatitude(parcelle.getLatitude());
            parcelleDTO.setLongitude(parcelle.getLongitude());
            parcelleDTO.setType_sol(parcelle.getType_sol());
            parcelleDTO.setType_culture_avant(parcelle.getType_culture_avant());
            parcelleDTO.setSurface(parcelle.getSurface());
            parcelleDTOs.add(parcelleDTO);
        }
        return parcelleDTOs;    }

    //get niova post
    @GetMapping("/getByType/{type_sol}")
    public List<ParcelleDTO> getByType(@PathVariable String type_sol){
        List<ParcelleEntity> parc =  parcelleService.getByType(type_sol);
        List<ParcelleDTO> parcelleDTOs = new ArrayList<>();
        for (ParcelleEntity parcelle : parc) {
            ParcelleDTO parcelleDTO = new ParcelleDTO();
            parcelleDTO.setCode_parcelle(parcelle.getCode_parcelle());
            parcelleDTO.setLatitude(parcelle.getLatitude());
            parcelleDTO.setLongitude(parcelle.getLongitude());
            parcelleDTO.setType_sol(parcelle.getType_sol());
            parcelleDTO.setType_culture_avant(parcelle.getType_culture_avant());
            parcelleDTO.setSurface(parcelle.getSurface());
            parcelleDTOs.add(parcelleDTO);
        }
        return parcelleDTOs;
    }

    @GetMapping("/getAvant/{type_culture_avant}")
    public List<ParcelleDTO> getTypeAvant(@PathVariable String type_culture_avant){
        List<ParcelleEntity> parc = parcelleService.getTypeAvant(type_culture_avant);
        List<ParcelleDTO> parcelleDTOs = new ArrayList<>();
        for (ParcelleEntity parcelle : parc) {
            ParcelleDTO parcelleDTO = new ParcelleDTO();
            parcelleDTO.setCode_parcelle(parcelle.getCode_parcelle());
            parcelleDTO.setLatitude(parcelle.getLatitude());
            parcelleDTO.setLongitude(parcelle.getLongitude());
            parcelleDTO.setType_sol(parcelle.getType_sol());
            parcelleDTO.setType_culture_avant(parcelle.getType_culture_avant());
            parcelleDTO.setSurface(parcelle.getSurface());
            parcelleDTOs.add(parcelleDTO);
        }
        return parcelleDTOs;
    }

    //ito le maka code_parcelle sy type_sol
    @GetMapping("/getCodeType")
    public List<CodeTypeDTO> getCodeType(){
        List<Object[]> parc =  parcelleService.getCodeType();
        List<CodeTypeDTO> codeTypeDTOS = new ArrayList<>();
        for (Object[] parcelle : parc) {
            CodeTypeDTO codeTypeDTO = new CodeTypeDTO();
            codeTypeDTO.setCode_parcelle((Integer) parcelle[0]);
            codeTypeDTO.setType_sol((String) parcelle[1]);
            codeTypeDTOS.add(codeTypeDTO);
        }
        return codeTypeDTOS;
    }


    @DeleteMapping("/delete/{code_parcelle}")
    public void deleteParcelle(@PathVariable Integer code_parcelle){
       parcelleService.deleteParcelle(code_parcelle);
    }

    @GetMapping("/getDispo")
    public List<ParcelleDTO> getDispo(){
        List<ParcelleEntity> parc = parcelleService.getDispo();
        List<ParcelleDTO> parcelleDTOs = new ArrayList<>();
        for (ParcelleEntity parcelle : parc) {
            ParcelleDTO parcelleDTO = new ParcelleDTO();
            parcelleDTO.setCode_parcelle(parcelle.getCode_parcelle());
            parcelleDTO.setLatitude(parcelle.getLatitude());
            parcelleDTO.setLongitude(parcelle.getLongitude());
            parcelleDTO.setType_sol(parcelle.getType_sol());
            parcelleDTO.setType_culture_avant(parcelle.getType_culture_avant());
            parcelleDTO.setSurface(parcelle.getSurface());
            parcelleDTOs.add(parcelleDTO);
        }
        return parcelleDTOs;
    }

    @GetMapping("/getPlanter")
    public List<Object[]> getPlanter(){
        return parcelleService.getPlanter();
    }
}
