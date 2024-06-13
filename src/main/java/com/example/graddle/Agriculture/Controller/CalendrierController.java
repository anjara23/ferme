package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.DTO.AgendaDTO;
import com.example.graddle.Agriculture.DTO.CalendrierDTO;
import com.example.graddle.Agriculture.Entities.CalendrierEntity;
import com.example.graddle.Agriculture.Payload.CalendrierRequest;
import com.example.graddle.Agriculture.Services.CalendrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/agriculture/calendrier")
@RequiredArgsConstructor
public class CalendrierController {

    private final CalendrierService calendrierService;

    @PostMapping("/addCalendrier")
    public void addCalendrier(@RequestBody CalendrierRequest calendrierRequest){
        calendrierService.addCalendrier(calendrierRequest);
    }

    @PutMapping("/updateCal/{id_calendrier}")
    public void updateCalendrier(@PathVariable Integer id_calendrier, @RequestBody CalendrierRequest calendrierRequest){
        calendrierService.updateCalendrier(id_calendrier, calendrierRequest);
    }

    @GetMapping("/getByMonth/{mois}")
    public List<CalendrierDTO> getByMonth(@PathVariable Integer mois){
        List<CalendrierEntity> cal = calendrierService.getByMonth(mois);
        List<CalendrierDTO> calendrierDTOS = new ArrayList<>();
        for(CalendrierEntity calendrier : cal) {
            CalendrierDTO calendrierDTO = new CalendrierDTO();
            calendrierDTO.setId_calendrier(calendrier.getId_calendrier());
            calendrierDTO.setId_plante(calendrier.getPlante().getId_plante());
            calendrierDTO.setActivite(calendrier.getActivite());
            calendrierDTO.setDate_debut(calendrier.getDate_debut());
            calendrierDTO.setDate_fin(calendrier.getDate_fin());
            calendrierDTO.setDescription(calendrier.getDescription());
            calendrierDTOS.add(calendrierDTO);
        }
        return calendrierDTOS;
    }

    @GetMapping("/getAll")
    public List<CalendrierEntity> getAll(){
        return calendrierService.getAll();
    }

    @GetMapping("/notifDeb")
    public List<String> notifDeb(){ return calendrierService.notifDeb(); }

    @GetMapping("/notifFin")
    public List<String> notifFin(){ return calendrierService.notifFin(); }

    @GetMapping("/getByDay/{date}")
    public List<AgendaDTO> getByDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<Object[]> dates = calendrierService.getByDay(date);
        List<AgendaDTO> agendaDTOS = new ArrayList<>();
        for (Object[] auj : dates) {
            AgendaDTO agendaDTO = new AgendaDTO();
            agendaDTO.setActivite((String) auj[0]);
            agendaDTO.setDate_debut((Date) auj[1]);
            agendaDTO.setDate_fin((Date) auj[2]);
            agendaDTO.setDescription((String) auj[3]);
            agendaDTOS.add(agendaDTO);
        }
        return agendaDTOS;
    }


}
