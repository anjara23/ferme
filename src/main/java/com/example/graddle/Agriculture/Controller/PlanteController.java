package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.DTO.IdTypeDTO;
import com.example.graddle.Agriculture.DTO.PlanteDTO;
import com.example.graddle.Agriculture.Entities.PlanteEntity;
import com.example.graddle.Agriculture.Payload.PlanteRequest;
import com.example.graddle.Agriculture.Services.PlanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/agriculture/plante")
@RequiredArgsConstructor
public class PlanteController {

    private final PlanteService planteService;

    @PostMapping("/addPlante")
    public void addPlante(@RequestBody PlanteRequest planteRequest){
        planteService.addPlante(planteRequest);
    }

    @PutMapping("/update/{id_plante}")
    public void updatePlante(@PathVariable Integer id_plante,@RequestBody PlanteRequest planteRequest){
        planteService.updatePlante(id_plante,planteRequest);
    }

    @GetMapping("/getAllP")
    public List<PlanteDTO> getAllPlante(){

        List<PlanteEntity> pl = planteService.getAllPlante();
        List<PlanteDTO> planteDTOS = new ArrayList<>();
        for (PlanteEntity plante : pl){
            PlanteDTO planteDTO = new PlanteDTO();
            planteDTO.setId_plante(plante.getId_plante());
            planteDTO.setType_plante(plante.getType_plante());
            planteDTO.setVariete(plante.getVariete());
            planteDTO.setDescription(plante.getDescription());
            planteDTO.setNbr_plante(plante.getNbr_plante());
            planteDTOS.add(planteDTO);

        }
        return planteDTOS;
    }

    @GetMapping("/getByType/{type_plante}")
    public List<PlanteDTO> getByTypePlante(@PathVariable String type_plante){
        List<PlanteEntity> pl = planteService.getByTypePlante(type_plante);
        List<PlanteDTO> planteDTOS = new ArrayList<>();
        for (PlanteEntity plante : pl){
            PlanteDTO planteDTO = new PlanteDTO();
            planteDTO.setId_plante(plante.getId_plante());
            planteDTO.setType_plante(plante.getType_plante());
            planteDTO.setVariete(plante.getVariete());
            planteDTO.setDescription(plante.getDescription());
            planteDTO.setNbr_plante(plante.getNbr_plante());
            planteDTOS.add(planteDTO);

        }
        return planteDTOS;

    }

    @GetMapping("/getByVariete/{variete}")
    public List<PlanteDTO> getByVariete(@PathVariable String variete){
        List<PlanteEntity> pl = planteService.getByVariete(variete);
        List<PlanteDTO> planteDTOS = new ArrayList<>();
        for (PlanteEntity plante : pl){
            PlanteDTO planteDTO = new PlanteDTO();
            planteDTO.setId_plante(plante.getId_plante());
            planteDTO.setType_plante(plante.getType_plante());
            planteDTO.setVariete(plante.getVariete());
            planteDTO.setDescription(plante.getDescription());
            planteDTO.setNbr_plante(plante.getNbr_plante());
            planteDTOS.add(planteDTO);

        }
        return planteDTOS;
    }

    //ito ilay sady maka id_plante no maka anle type_plante
    @GetMapping("/getIdType")
    public List<IdTypeDTO> getIdType(){
        List<Object[]> pl = planteService.getIdType();
        List<IdTypeDTO> idTypeDTOS = new ArrayList<>();
        for (Object[] plante : pl){
            IdTypeDTO idTypeDTO = new IdTypeDTO();
            idTypeDTO.setId_plante((Integer)plante[0]);
            idTypeDTO.setType_plante((String) plante[1]);
            idTypeDTOS.add(idTypeDTO);
        }
        return idTypeDTOS;
    }

    @DeleteMapping("/delete/{id_plante}")
    public void deletePlante(@PathVariable Integer id_plante){
        planteService.deletePlante(id_plante);
    }


}
