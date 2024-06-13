package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.DTO.CultureDTO;
import com.example.graddle.Agriculture.Entities.CultureEntity;
import com.example.graddle.Agriculture.Payload.CultureRequest;
import com.example.graddle.Agriculture.Services.CultureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/agriculture/culture")
@RequiredArgsConstructor
public class CultureController {

    private final CultureService cultureService;

    @PostMapping("/addCulture")
    public void addCulture(@RequestBody CultureRequest cultureRequest){
        cultureService.addCulture(cultureRequest);
    }

    @PutMapping("/doRecolte/{id_cultiver}")
    public void doRecolte(@PathVariable Integer id_cultiver, @RequestBody CultureRequest cultureRequest){
        cultureService.doRecolte(id_cultiver, cultureRequest);
    }

    @GetMapping("/getAllC")
    public List<CultureDTO> getAllC(){
        List<CultureEntity> cult = cultureService.getAllC();
        List<CultureDTO> cultureDTOS = new ArrayList<>();
        for(CultureEntity culture : cult){
            CultureDTO cultureDTO = new CultureDTO();
            cultureDTO.setId_cultiver(culture.getId_cultiver());
            cultureDTO.setCode_parcelle(culture.getParcelle().getCode_parcelle());
            cultureDTO.setId_plante(culture.getPlante().getId_plante());
            cultureDTO.setDate_plantation(culture.getDate_plantation());
            cultureDTO.setDate_production(culture.getDate_production());
            cultureDTO.setDate_recolte(culture.getDate_recolte());
            cultureDTO.setProduit_kg(cultureDTO.getProduit_kg());
            cultureDTO.setNb_planter(culture.getNb_planter());
            cultureDTO.setSurface_c(culture.getSurface_c());
            cultureDTO.setResultat_c(culture.getResultat_c());
            cultureDTOS.add(cultureDTO);
        }
        return cultureDTOS;
    }

    @GetMapping("/getByVar/{variete}")
    public List<CultureDTO> getByVariete(@PathVariable  String variete){

        List<CultureEntity> cult = cultureService.getByVariete(variete);
        List<CultureDTO> cultureDTOS = new ArrayList<>();
        for(CultureEntity culture : cult){
            CultureDTO cultureDTO = new CultureDTO();
            cultureDTO.setId_cultiver(culture.getId_cultiver());
            cultureDTO.setCode_parcelle(culture.getParcelle().getCode_parcelle());
            cultureDTO.setId_plante(culture.getPlante().getId_plante());
            cultureDTO.setDate_plantation(culture.getDate_plantation());
            cultureDTO.setDate_production(culture.getDate_production());
            cultureDTO.setDate_recolte(culture.getDate_recolte());
            cultureDTO.setProduit_kg(cultureDTO.getProduit_kg());
            cultureDTO.setNb_planter(culture.getNb_planter());
            cultureDTO.setSurface_c(culture.getSurface_c());
            cultureDTO.setResultat_c(culture.getResultat_c());
            cultureDTOS.add(cultureDTO);
        }
        return cultureDTOS;    }

    @GetMapping("/getFinished")
    public List<CultureDTO> getFinished(){
        List<CultureEntity> cult = cultureService.getFinished();
        List<CultureDTO> cultureDTOS = new ArrayList<>();
        for(CultureEntity culture : cult){
            CultureDTO cultureDTO = new CultureDTO();
            cultureDTO.setId_cultiver(culture.getId_cultiver());
            cultureDTO.setCode_parcelle(culture.getParcelle().getCode_parcelle());
            cultureDTO.setId_plante(culture.getPlante().getId_plante());
            cultureDTO.setDate_plantation(culture.getDate_plantation());
            cultureDTO.setDate_production(culture.getDate_production());
            cultureDTO.setDate_recolte(culture.getDate_recolte());
            cultureDTO.setProduit_kg(cultureDTO.getProduit_kg());
            cultureDTO.setNb_planter(culture.getNb_planter());
            cultureDTO.setSurface_c(culture.getSurface_c());
            cultureDTO.setResultat_c(culture.getResultat_c());
            cultureDTOS.add(cultureDTO);
        }
        return cultureDTOS;    }

    @GetMapping("/getByMonth/{mois}")
    public List<CultureDTO> getByMonth(@PathVariable Integer mois){

        List<CultureEntity> cult = cultureService.getByMonth(mois);
        List<CultureDTO> cultureDTOS = new ArrayList<>();
        for(CultureEntity culture : cult){
            CultureDTO cultureDTO = new CultureDTO();
            cultureDTO.setId_cultiver(culture.getId_cultiver());
            cultureDTO.setCode_parcelle(culture.getParcelle().getCode_parcelle());
            cultureDTO.setId_plante(culture.getPlante().getId_plante());
            cultureDTO.setDate_plantation(culture.getDate_plantation());
            cultureDTO.setDate_production(culture.getDate_production());
            cultureDTO.setDate_recolte(culture.getDate_recolte());
            cultureDTO.setProduit_kg(cultureDTO.getProduit_kg());
            cultureDTO.setNb_planter(culture.getNb_planter());
            cultureDTO.setSurface_c(culture.getSurface_c());
            cultureDTO.setResultat_c(culture.getResultat_c());
            cultureDTOS.add(cultureDTO);
        }
        return cultureDTOS;    }

    @DeleteMapping("/delete/{id_cultiver}")
    public void deleteCulture(@PathVariable Integer id_cultiver){
        cultureService.deleteCulture(id_cultiver);
    }
}
