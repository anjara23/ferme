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
    public void addCalendrier(CalendrierRequest calendrierRequest) {
        try {
            CalendrierEntity cal = new CalendrierEntity();

            PlanteEntity plante = new PlanteEntity();
            plante.setId_plante(calendrierRequest.getId_plante());

            cal.setPlante(plante);
            cal.setActivite(calendrierRequest.getActivite());
            cal.setDate_debut(calendrierRequest.getDate_debut());
            cal.setDate_fin(calendrierRequest.getDate_fin());
            cal.setDescription(calendrierRequest.getDescription());

            calendrierRepository.save(cal);
        } catch (Exception e) {
            System.err.println("An error occurred while adding the calendar: " + e.getMessage());
            throw new RuntimeException("Failed to add calendar", e);
        }
    }


    public void updateCalendrier(Integer id_calendrier, CalendrierRequest calendrierRequest) {
        try {
            Optional<CalendrierEntity> ca = calendrierRepository.findById(id_calendrier);
            if (!ca.isPresent()) {
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
        } catch (Exception e) {
            System.err.println("An error occurred while updating the calendar: " + e.getMessage());
            throw new RuntimeException("Failed to update calendar", e);
        }
    }

    public List<CalendrierEntity> getByMonth(Integer mois) {
        try {
            return calendrierRepository.getByMonth(mois);
        } catch (Exception e) {
            System.err.println("An error occurred while fetching the calendars by month: " + e.getMessage());
            throw new RuntimeException("Failed to fetch calendars by month", e);
        }
    }

    public List<CalendrierEntity> getAll() {
        try {
            return calendrierRepository.getAll();
        } catch (Exception e) {
            System.err.println("An error occurred while fetching all calendars: " + e.getMessage());
            throw new RuntimeException("Failed to fetch all calendars", e);
        }
    }

    public List<String> notifDeb() {
        try {
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
        } catch (Exception e) {
            System.err.println("An error occurred while generating notifications: " + e.getMessage());
            throw new RuntimeException("Failed to generate notifications", e);
        }
    }

    public List<String> notifFin() {
        try {
            List<String> notifications = new ArrayList<>();
            Date auj = new Date();
            List<CalendrierEntity> fi = calendrierRepository.getFin(auj);

            if (fi != null && !fi.isEmpty()) {
                for (CalendrierEntity fin : fi) {
                    String activite = fin.getActivite();
                    String notif = "Fin de l'activité : " + activite;
                    notifications.add(notif);
                }
            }

            return notifications;
        } catch (Exception e) {
            System.err.println("An error occurred while generating end activity notifications: " + e.getMessage());
            throw new RuntimeException("Failed to generate end activity notifications", e);
        }
    }


    public List<Object[]> getByDay(Date date) {
        try {
            return calendrierRepository.getByDay(date);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch calendars by day", e);
        }
    }



}
