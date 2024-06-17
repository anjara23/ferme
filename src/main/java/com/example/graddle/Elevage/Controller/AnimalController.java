package com.example.graddle.Elevage.Controller;

import com.example.graddle.Elevage.DTO.AnimalDTO;
import com.example.graddle.Elevage.DTO.DiagAniDTO;
import com.example.graddle.Elevage.DTO.EssentialDTO;
import com.example.graddle.Elevage.Entities.SanteEntity;
import com.example.graddle.Elevage.Response.AnimalSanteResponse;
import com.example.graddle.Elevage.Entities.AnimalEntity;
import com.example.graddle.Elevage.Payload.AnimalSanteRequest;
import com.example.graddle.Elevage.Payload.CroissanceRequest;
import com.example.graddle.Elevage.Services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/elevage/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("/add")
    public void addData(@RequestBody AnimalSanteRequest animalSanteRequest){
       animalService.addData(animalSanteRequest);
    }

    @PutMapping("/update/{id_animal}")
    public AnimalSanteResponse updateData(@PathVariable Integer id_animal, @RequestBody AnimalSanteRequest animalSanteRequest){
        return animalService.updateData(id_animal, animalSanteRequest);
    }

    @GetMapping("/get/{id_animal}")
    public AnimalEntity getAnimal(@PathVariable Integer id_animal){
        return animalService.getAnimal(id_animal);
    }

    @GetMapping("/getAll")
    public List<EssentialDTO>getAllData(){
        List<Object[]> animaux =  animalService.getAllData();
        List<EssentialDTO> animalDTOS = new ArrayList<>();
        for(Object[] ani : animaux){
            EssentialDTO animalDTO = new EssentialDTO();
            animalDTO.setId_animal((Integer) ani[0]);
            animalDTO.setEspece((String) ani[1]);
            animalDTO.setNom((String) ani[2]);
            animalDTO.setSexe((String) ani[3]);
            animalDTO.setStatut((String) ani[4]);
            animalDTO.setEtat((Integer) ani[5]);
            animalDTOS.add(animalDTO);
        }
        return animalDTOS;
    }

    @GetMapping("/about/{id_animal}")
    public SanteEntity getAnimalSante(@PathVariable Integer id_animal){
        return animalService.getAnimalSante(id_animal);
    }

    @DeleteMapping("/delete/{id_animal}")
    public void delete(@PathVariable Integer id_animal){
        animalService.delete(id_animal);
    }


    //ttl y compris les morts
    @GetMapping("/getTTl")
    public Integer ttlAni(){
        return animalService.ttlAni();
    }

    @GetMapping("/getDec")
    public List<AnimalDTO> getDec(){
        List<AnimalEntity> animaux = animalService.getDec();
        List<AnimalDTO> animalDTOS = new ArrayList<>();
        for(AnimalEntity animal : animaux){
            AnimalDTO animalDTO = new AnimalDTO();
            animalDTO.setId_animal(animal.getId_animal());
            animalDTO.setEspece(animal.getEspece());
            animalDTO.setRace(animal.getRace());
            animalDTO.setNom(animal.getNom());
            animalDTO.setDatenaiss(animal.getDatenaiss());
            animalDTO.setSexe(animal.getSexe());
            animalDTO.setStatut(animal.getStatut());
            animalDTO.setDate_enre(animal.getDate_enre());
            animalDTO.setDate_vente(animal.getDate_vente());
            animalDTO.setDate_dec(animal.getDate_dec());
            animalDTO.setAge(animal.getAge());
            animalDTO.setPoids(animal.getPoids());
            animalDTOS.add(animalDTO);
        }
        return animalDTOS;
    }

    @GetMapping( "/getnbDec")
    public Integer nbDec(){ return  animalService.nbDec();}

    @GetMapping("/getByEspece/{espece}")
    public Integer nbParEspece(@PathVariable String espece){ return  animalService.nbParEspece(espece); }

    @GetMapping("/AchatEspece/{espece}")
    public Integer AchatEspece( @PathVariable String espece){
        return animalService.AchatEspece(espece);
    }

    @GetMapping("/nbVendu/{espece}")
    public  Integer nbVendu( @PathVariable String espece){ return animalService.nbVendu(espece); }

    //get lasa post
    @PostMapping("/diagC")
    public Double diagCroiss ( @RequestBody CroissanceRequest request){ return  animalService.diagCroiss(request); }

    @GetMapping("/diagAni/{espece}")
    public List<DiagAniDTO> diagAni(@PathVariable String espece){
        List<Object[]> animaux =  animalService.diagAni(espece);
        List<DiagAniDTO> diagAniDTOS = new ArrayList<>();
        for(Object[] ani : animaux){
            DiagAniDTO diagAniDTO = new DiagAniDTO();


            if (ani[0] instanceof Long) {
                diagAniDTO.setAchat(((Long) ani[0]).intValue());
            }
            if (ani[1] instanceof Long) {
                diagAniDTO.setVendu(((Long) ani[1]).intValue());
            }
            if (ani[2] instanceof Long) {
                diagAniDTO.setDeces(((Long) ani[2]).intValue());
            }
            if (ani[3] instanceof Long) {
                diagAniDTO.setNouv(((Long) ani[3]).intValue());
            }

            diagAniDTOS.add(diagAniDTO);
        }
        return diagAniDTOS;
    }


    @GetMapping("/getBy/{espece}")
    public List<AnimalDTO> getByEspece(@PathVariable String espece){
        List<AnimalEntity> animaux =  animalService.getByEspece(espece);
        List<AnimalDTO> animalDTOS = new ArrayList<>();
        for(AnimalEntity animal : animaux){
            AnimalDTO animalDTO = new AnimalDTO();
            animalDTO.setId_animal(animal.getId_animal());
            animalDTO.setEspece(animal.getEspece());
            animalDTO.setRace(animal.getRace());
            animalDTO.setNom(animal.getNom());
            animalDTO.setDatenaiss(animal.getDatenaiss());
            animalDTO.setSexe(animal.getSexe());
            animalDTO.setStatut(animal.getStatut());
            animalDTO.setDate_enre(animal.getDate_enre());
            animalDTO.setDate_vente(animal.getDate_vente());
            animalDTO.setDate_dec(animal.getDate_dec());
            animalDTO.setAge(animal.getAge());
            animalDTO.setPoids(animal.getPoids());
            animalDTOS.add(animalDTO);
        }
        return animalDTOS;

    }
}


