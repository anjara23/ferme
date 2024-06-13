package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.CalendrierEntity;
import com.example.graddle.Agriculture.Entities.PlanteEntity;
import com.example.graddle.Agriculture.Payload.CalendrierRequest;
import com.example.graddle.Agriculture.Repository.CalendrierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendrierService {

    private final CalendrierRepository calendrierRepository;

    //ito le ajout manuelle
    public void addCalendrier(CalendrierRequest calendrierRequest){
        CalendrierEntity cal = new CalendrierEntity();

        PlanteEntity plante = new PlanteEntity();
        plante.setId_plante(calendrierRequest.getId_plante());

        cal.setPlante(plante);
        cal.setActivite(calendrierRequest.getActivite());
        cal.setDate_debut(calendrierRequest.getDate_debut());
        cal.setDate_fin(calendrierRequest.getDate_fin());
        cal.setDescription(calendrierRequest.getDescription());

        calendrierRepository.save(cal);
    }

    public void updateCalendrier(Integer id_calendrier, CalendrierRequest calendrierRequest){
       Optional<CalendrierEntity> ca = calendrierRepository.findById(id_calendrier);
       if(!ca.isPresent()){
           throw new EntityNotFoundException("CalendrierEntity with id " + id_calendrier + " not found");
       }
       CalendrierEntity cal = ca.get();

        PlanteEntity plante = new PlanteEntity();
        plante.setId_plante(calendrierRequest.getId_plante());

        cal.setPlante(plante);
        cal.setActivite(calendrierRequest.getActivite());
        cal.setDate_debut(calendrierRequest.getDate_debut());
        cal.setDate_fin(calendrierRequest.getDate_fin());
        cal.setDescription(calendrierRequest.getDescription());

        calendrierRepository.save(cal);



    }

    public List<CalendrierEntity> getByMonth(Integer mois){
        return calendrierRepository.getByMonth(mois);
    }

    public List<String> notifDeb() {
        List<String> notifications = new ArrayList<>();
        Date auj = new Date();
        List<CalendrierEntity> deb = calendrierRepository.getDeb(auj);

        if (deb != null && !deb.isEmpty()) {
            for (CalendrierEntity debut : deb) {
                String activite = debut.getActivite();
                String notif = "Début de l'activité : " + activite;
                notifications.add(notif);
            }
        }

        return notifications;
    }

    public List<String> notifFin() {
        List<String> notifications = new ArrayList<>();
        Date auj = new Date();
        List<CalendrierEntity> fi = calendrierRepository.getFin(auj);

        if (fi != null && !fi.isEmpty()) {
            for(CalendrierEntity fin : fi){
                String activite = fin.getActivite();
                String notif = "Fin de l'activité : " +activite;
                notifications.add(notif);
            }
        }

        return notifications;

    }


    public List<Object[]> getByDay(Date date){
        return calendrierRepository.getByDay(date);
    }


}
