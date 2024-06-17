package com.example.graddle.Elevage.Services;

import com.example.graddle.Elevage.Entities.SanteEntity;
import com.example.graddle.Elevage.Payload.*;
import com.example.graddle.Elevage.Response.AnimalSanteResponse;
import com.example.graddle.Elevage.Entities.AnimalEntity;
import com.example.graddle.Elevage.Entities.ProduitEntity;
import com.example.graddle.Elevage.Repository.AnimalRepository;
import com.example.graddle.Elevage.Repository.ProductionRepository;
import com.example.graddle.Elevage.Repository.SanteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final SanteRepository santeRepository;
    private final ProduitService produitService;

    private final ProductionRepository productionRepository;


    public void addData(AnimalSanteRequest animalSanteRequest) {
        try {
            AnimalRequest animalRequest = animalSanteRequest.getAnimalRequest();
            SanteRequest santeRequest = animalSanteRequest.getSanteRequest();

            AnimalEntity ani = new AnimalEntity();
            ani.setEspece(animalRequest.getEspece());
            ani.setRace(animalRequest.getRace());
            ani.setNom(animalRequest.getNom());
            ani.setDatenaiss(animalRequest.getDatenaiss());
            ani.setSexe(animalRequest.getSexe());
            ani.setStatut(animalRequest.getStatut());
            ani.setDate_enre(new Date());//date auj
            ani.setDate_vente(animalRequest.getDate_vente());
            ani.setDate_dec(animalRequest.getDate_dec());

            // Si la date de décès est null, on la met explicitement à null
            if (ani.getDate_dec() == null) {
                ani.setDate_dec(null);
            }

            Date nais = animalRequest.getDatenaiss();
            Date auj = new Date();
            Integer age = calculateDateDifference(nais, auj);
            ani.setAge(age);
            ani.setPoids(animalRequest.getPoids());

            AnimalEntity saveData = animalRepository.save(ani);

            // Ajout de l'entité Sante si l'enregistrement de l'animal a réussi
            if (saveData != null) {
                SanteEntity sante = new SanteEntity();
                sante.setId_animal(saveData);

                // Logique conditionnelle pour définir les valeurs de l'entité Sante
                if (santeRequest.isVaccin()) {
                    sante.setDate_vacc(santeRequest.getDate_vacc());
                } else {
                    sante.setDate_vacc(null);
                }

                if (santeRequest.isVermifuge()) {
                    sante.setDate_verm(santeRequest.getDate_verm());
                } else {
                    sante.setDate_verm(null);
                }

                if ("mâle".equals(animalRequest.getSexe())) {
                    sante.setGestation(null);
                } else {
                    sante.setGestation(santeRequest.isGestation());
                }

                if (santeRequest.getMaladie() == null && santeRequest.getBlessure() == null) {
                    sante.setTraitement(null);
                    sante.setDate_trait(null);
                    sante.setEtat(2); // En bonne santé
                } else {
                    sante.setEtat(1); // En traitement
                    sante.setTraitement(santeRequest.getTraitement());
                    sante.setDate_trait(santeRequest.getDate_trait());
                }

                sante.setVaccin(santeRequest.isVaccin());
                sante.setVermifuge(santeRequest.isVermifuge());
                sante.setMaladie(santeRequest.getMaladie());
                sante.setBlessure(santeRequest.getBlessure());

                santeRepository.save(sante);
            }

            // Ajout de produit si l'animal est vendu et non décédé
            if (ani.getDate_vente() != null && ani.getDate_dec() == null) {
                ProduitRequest produitRequest = new ProduitRequest();
                produitRequest.setType_produit("animal");
                produitRequest.setQuantite(ani.getPoids());
                produitRequest.setQualite(2); // Avis personnel, à adapter si nécessaire
                produitRequest.setDate_prod(ani.getDate_vente());
                produitRequest.setEspecef(ani.getEspece());

                produitService.addProduit(produitRequest);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to add animal and related health information", e);
        }
    }

    public AnimalSanteResponse updateData(Integer id_animal, AnimalSanteRequest animalSanteRequest) {
        try {
            if (animalSanteRequest == null || animalSanteRequest.getAnimalRequest() == null) {
                throw new IllegalArgumentException("AnimalSanteRequest or AnimalRequest cannot be null");
            }

            Optional<AnimalEntity> aniOptional = animalRepository.findById(id_animal);
            if (!aniOptional.isPresent()) {
                throw new EntityNotFoundException("AnimalEntity with id " + id_animal + " not found");
            }
            AnimalEntity ani = aniOptional.get();

            String espece1 = ani.getEspece();
            Date date_enre = ani.getDate_enre();

            // Mettre à jour les propriétés de l'animal avec les valeurs de AnimalRequest
            AnimalRequest animalRequest = animalSanteRequest.getAnimalRequest();
            ani.setEspece(animalRequest.getEspece());
            ani.setRace(animalRequest.getRace());
            ani.setNom(animalRequest.getNom());
            ani.setDatenaiss(animalRequest.getDatenaiss());
            ani.setSexe(animalRequest.getSexe());
            ani.setStatut(animalRequest.getStatut());
            ani.setDate_vente(animalRequest.getDate_vente());
            ani.setDate_dec(animalRequest.getDate_dec());

            Date nais = animalRequest.getDatenaiss();
            Date auj = new Date();
            Integer age = calculateDateDifference(nais, auj);
            ani.setAge(age);

            ani.setPoids(animalRequest.getPoids());

            ani = animalRepository.save(ani);

            // Ajout de produit si l'animal est vendu et non décédé
            if (ani.getDate_vente() != null && ani.getDate_dec() == null) {
                ProduitRequest produitRequest = new ProduitRequest();
                produitRequest.setType_produit("animal");
                produitRequest.setQuantite(1.0); // Par défaut, quantité = 1.0
                produitRequest.setQualite(2); // Avis personnel, à adapter si nécessaire
                produitRequest.setDate_prod(ani.getDate_vente());
                produitRequest.setEspecef(ani.getEspece());

                // Vérification si cet animal existe déjà comme produit dans la table produit
                ProduitEntity existe = produitService.find(produitRequest.getType_produit(), produitRequest.getEspecef());
                if (existe != null) {
                    Integer id_produit = existe.getId_produit();
                    // Incrémentation de la quantité
                    Double nouvelleQuantite = produitRequest.getQuantite() + existe.getQuantite();
                    produitRequest.setQuantite(nouvelleQuantite);
                    // Mise à jour de l'espèce dans le produit
                    produitService.updateEspece(espece1, ani.getEspece());
                    produitRequest.setEspecef(ani.getEspece());
                    produitService.updateProduit(id_produit, produitRequest);
                } else {
                    produitService.addProduit(produitRequest);
                }
            }

            // Récupérer la SanteEntity
            List<SanteEntity> santeList = santeRepository.findByIdAnimal(id_animal);
            if (santeList.isEmpty()) {
                throw new EntityNotFoundException("SanteEntity with id_animal " + id_animal + " not found");
            }

            SanteEntity sante = santeList.get(0);
            SanteRequest santeRequest = animalSanteRequest.getSanteRequest();

            if (santeRequest.isVaccin()) {
                sante.setDate_vacc(santeRequest.getDate_vacc());
            } else {
                sante.setDate_vacc(null);
            }

            if (santeRequest.isVermifuge()) {
                sante.setDate_verm(santeRequest.getDate_verm());
            } else {
                sante.setDate_verm(null);
            }

            if ("mâle".equals(animalRequest.getSexe())) {
                sante.setGestation(null);
            } else {
                sante.setGestation(santeRequest.isGestation());
            }

            if (santeRequest.getMaladie() == null && santeRequest.getBlessure() == null) {
                sante.setTraitement(null);
                sante.setDate_trait(null);
                sante.setEtat(2); // Bonne santé
            } else {
                sante.setEtat(1); // En traitement
                sante.setTraitement(santeRequest.getTraitement());
                sante.setDate_trait(santeRequest.getDate_trait());
            }

            sante.setVaccin(santeRequest.isVaccin());
            sante.setVermifuge(santeRequest.isVermifuge());
            sante.setMaladie(santeRequest.getMaladie());
            sante.setBlessure(santeRequest.getBlessure());

            sante = santeRepository.save(sante);

            AnimalSanteResponse response = new AnimalSanteResponse();
            response.setAnimal(ani);
            response.setSante(sante);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update animal and related health information", e);
        }
    }

    public void delete(Integer id_animal) {
        try {

            List<SanteEntity> santeList = santeRepository.findByIdAnimal(id_animal);
            if (!santeList.isEmpty()) {
                SanteEntity sante = santeList.get(0);
                santeRepository.delete(sante);
            }

            Optional<AnimalEntity> optionalAnimal = animalRepository.findById(id_animal);
            if (optionalAnimal.isPresent()) {
                AnimalEntity animal = optionalAnimal.get();
                animalRepository.delete(animal);
            } else {
                throw new EntityNotFoundException("AnimalEntity with id " + id_animal + " not found");
            }



        } catch (Exception e) {
            throw new RuntimeException("Failed to delete animal and its health data", e);
        }
    }

    //calcul age
    public static Integer calculateDateDifference(Date startDate, Date endDate) {
        long diffInMillies = endDate.getTime() - startDate.getTime();

        long diffInDaysLong = diffInMillies / (24 * 60 * 60 * 1000);

        Integer diffInDays = (int) diffInDaysLong;

        return diffInDays;
    }

    // nb total des animaux
    public Integer ttlAni() {
        try {
            return animalRepository.ttlAni();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve total count of animals", e);
        }
    }


    // nb des animaux morts et liste des animaux morts animalRepository.getDec()
    public List<AnimalEntity> getDec() {
        try {
            return animalRepository.getDec();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }

    public Integer nbDec(){
        try {
            return animalRepository.nbDec();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }

    public Integer nbParEspece(String espece){
        try {
            return animalRepository.nbParEspece(espece);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }

    public List<AnimalEntity> getByEspece(String espece){
        try {
            return animalRepository.getByEspece(espece);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }


    /*nb animal achetés par espèces
    tsy aiko na acheté na Achat le statut any fa raha miova de ary am requête ary am AnimalRepository no manova*/
    public Integer AchatEspece(String espece){
        try {
            return animalRepository.getAchatEspece(espece);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }

    //nb animaux vendus par espèce
    public  Integer nbVendu(String espece){
        try {
            return animalRepository.nbVendu(espece);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }

    //le liste des vivants
    public List<Object[]> getAllData(){
        try {
            return animalRepository.getEssentiel();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve deceased animals", e);
        }
    }

    //get Animal by id
    public AnimalEntity getAnimal(Integer id_animal) {
        try {
            Optional<AnimalEntity> optional = animalRepository.findById(id_animal);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new EntityNotFoundException("AnimalEntity with id " + id_animal + " not found");
            }
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve AnimalEntity with id " + id_animal, e);
        }
    }

    //info personnels
    public SanteEntity getAnimalSante(Integer id_animal) {
        try {
            List<SanteEntity> santeList = santeRepository.findByIdAnimal(id_animal);

            if (!santeList.isEmpty()) {
                return santeList.get(0);
            } else {
                return null; // Aucune entité SanteEntity trouvée, retourne null comme spécifié
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve SanteEntity for Animal with id " + id_animal, e);
        }
    }


    //diagramme Croissance
    public Double diagCroiss(CroissanceRequest request) {
        try {
            // Validation des paramètres
            if (request.getA() <= request.getB()) {
                throw new IllegalArgumentException("a doit être supérieur à b");
            }
            if (request.getC() <= request.getD()) {
                throw new IllegalArgumentException("c doit être supérieur à d");
            }

            // Calculer le résultat de l'expression (a - b) / (c - d)
            return (request.getA() - request.getB()) / (request.getC() - request.getD());

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du calcul de la croissance : " + e.getMessage(), e);
        }
    }

    //diagramme Animaux
    public List<Object[]> diagAni(String espece) {
        try {
            return animalRepository.diagAni(espece);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des diagnostics pour l'espèce " + espece + ": " + e.getMessage(), e);
        }
    }


}

