package com.example.graddle.Agriculture.Controller;


import com.example.graddle.Agriculture.DTO.DiagDTO;
import com.example.graddle.Agriculture.DTO.Stade_pDTO;
import com.example.graddle.Agriculture.Entities.Stade_pEntity;
import com.example.graddle.Agriculture.Payload.Stade_pRequest;
import com.example.graddle.Agriculture.Services.Stade_pService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/agriculture/stade_p")
@RequiredArgsConstructor
public class Stade_pController {

    private final Stade_pService stade_pService;

    @PostMapping("/addStade")
    public void addStade(@RequestBody Stade_pRequest stade_pRequest){
        stade_pService.addStade(stade_pRequest);
    }

    @PutMapping("/updateS/{id_stade}")
    public void updateStade(@PathVariable Integer id_stade, @RequestBody Stade_pRequest stade_pRequest){
        stade_pService.updateStade(id_stade,stade_pRequest);
    }

    @GetMapping("/diagSt/{id_cultiver}")
    public List<DiagDTO> diagSt(@PathVariable Integer id_cultiver){
        List<Object[]>  st = stade_pService.diagSt(id_cultiver);
        List<DiagDTO> diagDTOS = new ArrayList<>();
        for(Object[] stade : st){
            DiagDTO diagDTO = new DiagDTO();
            diagDTO.setEtape((String) stade[0]);
            diagDTO.setDate_debut((Date) stade[1]);
            diagDTO.setDate_fin((Date) stade[2]);
            diagDTO.setBesoin_e((Double) stade[3]);
            diagDTOS.add(diagDTO);
        }
        return diagDTOS;
    }

    @GetMapping("/getAll")
    public List<Stade_pDTO> getAll(){
        List<Object[]> stades = stade_pService.getAll();
        List<Stade_pDTO> stade_pDTOS = new ArrayList<>();
        for (Object[] stade : stades){
            Stade_pDTO stade_pDTO = new Stade_pDTO();
            stade_pDTO.setId_cultiver((Integer) stade[0]);
            stade_pDTO.setEtape((String) stade[1]);
            stade_pDTO.setDate_debut((Date) stade[2]);
            stade_pDTO.setDate_fin((Date) stade[3]);
            stade_pDTO.setBesoin_e((Double) stade[4]);
            stade_pDTOS.add(stade_pDTO);

        }
        return stade_pDTOS;
    }

}
