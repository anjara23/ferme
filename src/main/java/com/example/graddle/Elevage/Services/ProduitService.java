package com.example.graddle.Elevage.Services;

import com.example.graddle.Elevage.Payload.ProduitRequest;
import com.example.graddle.Elevage.Repository.AnimalRepository;
import com.example.graddle.Elevage.Entities.AnimalEntity;
import com.example.graddle.Elevage.Entities.ProductionEntity;
import com.example.graddle.Elevage.Entities.ProduitEntity;
import com.example.graddle.Elevage.Payload.ProductionRequest;
import com.example.graddle.Elevage.Repository.ProductionRepository;
import com.example.graddle.Elevage.Repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;

    private final ProductionRepository productionRepository;

    private final AnimalRepository animalRepository;

    private final ProductionService productionService;


    public void addProduit(ProduitRequest produitRequest) {
        try {
            ProduitEntity existe = find(produitRequest.getType_produit(), produitRequest.getEspecef());
            if (existe != null) {
                Integer id_produit = existe.getId_produit();
                // Incrémenter la quantité
                Double nouvelleQuantite = produitRequest.getQuantite() + existe.getQuantite();
                produitRequest.setQuantite(nouvelleQuantite);
                updateProduit(id_produit, produitRequest);
            } else {
                String especeF = produitRequest.getEspecef();
                AnimalEntity isdead = animalRepository.Ifnotdec(especeF);
                if (isdead != null) {
                    ProduitEntity produit = new ProduitEntity();
                    produit.setType_produit(produitRequest.getType_produit());
                    produit.setQuantite(produitRequest.getQuantite());
                    produit.setQualite(produitRequest.getQualite());
                    produit.setDate_prod(produitRequest.getDate_prod() != null ? produitRequest.getDate_prod() : new Date());
                    produit.setEspecef(especeF);
                    ProduitEntity prood = produitRepository.save(produit);
                    if (prood != null) {
                        Integer mois = null;
                        // Obtention du mois de la date de production
                        Date dateProd = produit.getDate_prod();
                        if (dateProd != null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(dateProd);
                            mois = calendar.get(Calendar.MONTH) + 1; // Obtention du mois (1-12)
                        }
                        ProductionRequest request = new ProductionRequest();
                        request.setId_produit(prood.getId_produit());
                        request.setType_produit(produit.getType_produit());
                        request.setEspece(produit.getEspecef());
                        request.setMois(mois);
                        ProductionEntity production = productionService.addKPI(request);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout du produit", e);
        }
    }

    public void updateProduit(Integer id_produit, ProduitRequest produitRequest) {
        try {
            Optional<ProduitEntity> pro = produitRepository.findById(id_produit);
            if (!pro.isPresent()) {
                throw new EntityNotFoundException("ProduitEntity with id " + id_produit + " not found");
            }
            ProduitEntity prod = pro.get();

            prod.setType_produit(produitRequest.getType_produit());
            prod.setQuantite(produitRequest.getQuantite());
            prod.setQualite(produitRequest.getQualite());

            if (produitRequest.getDate_prod() == null) {
                prod.setDate_prod(new Date());
            }

            prod.setEspecef(produitRequest.getEspecef());

            ProduitEntity produit = produitRepository.save(prod);

            Integer mois = null;
            Date dateProd = produit.getDate_prod();
            if (dateProd != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateProd);
                mois = calendar.get(Calendar.MONTH) + 1; // Les mois en Java sont 0-based, donc ajouter 1
            } else {
                System.out.println("Erreur : `dateProd` est null dans ProduitEntity");
            }
            System.out.println("Mois calculé : " + mois);

            // Vérifier que `mois` n'est pas nul avant de continuer
            if (mois != null) {
                // Créer un objet ProductionRequest pour passer à la méthode updateKPI
                ProductionRequest request = new ProductionRequest();
                request.setId_produit(id_produit);
                request.setType_produit(produit.getType_produit());
                request.setEspece(produit.getEspecef());
                request.setMois(mois);

                // Appeler la méthode updateKPI avec le ProductionRequest créé
                productionService.updateKPI(request);

            } else {
                System.out.println("Erreur : Le mois est nul, ne peut pas mettre à jour KPI.");
            }
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la mise à jour du produit avec id " + id_produit, e);
        }
    }

    public void updateEspece(String espece1, String espece2) {
        try {
            List<ProduitEntity> list = produitRepository.getByEspece(espece1);
            for (ProduitEntity produit : list) {
                produit.setEspecef(espece2);
            }
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la mise à jour des produits avec l'espèce " + espece1 + " vers " + espece2, e);
        }
    }

    public void deleteProduit(Integer id_produit) {
        try {
            // Supprimer l'entité ProductionEntity liée à id_produit
            Optional<ProductionEntity> production = productionRepository.findById_produit(id_produit);
            if (production.isPresent()) {
                ProductionEntity product = production.get();
                productionRepository.delete(product);
            }

            // Supprimer l'entité ProduitEntity liée à id_produit
            Optional<ProduitEntity> produit = produitRepository.findById(id_produit);
            if (produit.isPresent()) {
                ProduitEntity pro = produit.get();
                produitRepository.delete(pro);
            }
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la suppression du produit avec l'ID " + id_produit, e);
        }
    }

    public List<ProduitEntity> getAllProd() {
        try {
            return produitRepository.getAllProd();
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la récupération de tous les produits", e);
        }
    }

    public List<Object[]> getByType(String type_produit) {
        try {
            return produitRepository.getByType(type_produit);
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la récupération des produits par type", e);
        }
    }


    //diagramme qualité produit
    //tsy nataoko par mois aloha izy eto fa raha mila atao par mois de signaleo azafady
    public List<Object[]> diagQ(String type_prod) {
        try {
            return produitRepository.diagQ(type_prod);
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la récupération des données pour le diagnostic de quantité", e);
        }
    }

    public ProduitEntity find(String type_produit, String especef) {
        try {
            return produitRepository.find(type_produit, especef);
        } catch (Exception e) {
            // Gestion de l'exception
            throw new RuntimeException("Erreur lors de la recherche du produit avec type_produit : " + type_produit + " et especef : " + especef, e);
        }
    }



}
