package com.example.graddle.Elevage.Controller;


import com.example.graddle.Elevage.DTO.DiagProDTO;
import com.example.graddle.Elevage.DTO.ProduitDTO;
import com.example.graddle.Elevage.Payload.ProduitRequest;
import com.example.graddle.Elevage.Services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/elevage/produit")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping("/addProduit")
    public void addProduit(@RequestBody ProduitRequest produitRequest){
        produitService.addProduit(produitRequest);
    }

    @PutMapping("/update/{id_produit}")
    public void updateProduit(@PathVariable Integer id_produit, @RequestBody ProduitRequest produitRequest){
        produitService.updateProduit(id_produit, produitRequest);
    }

    @DeleteMapping("/delete/{id_produit}")
    public void deleteProduit(@PathVariable Integer id_produit){
        produitService.deleteProduit(id_produit);
    }

    @GetMapping("/getAll")
    public List<ProduitDTO> getAllProd() {
        List<Object[]> produits = produitService.getAllProd();
        List<ProduitDTO> produitDTOS = new ArrayList<>();
        for (Object[] produit : produits) {
            ProduitDTO produitDTO = new ProduitDTO();
            produitDTO.setId_produit((Integer) produit[0]);
            produitDTO.setType_produit((String) produit[1]);
            if (produit[2] != null) {
                produitDTO.setQuantite(((Number) produit[2]).doubleValue());
            } else {
                produitDTO.setQuantite(null);
            }
            produitDTO.setQualite((Integer) produit[3]);
            produitDTO.setDate_prod((Date) produit[4]);
            produitDTOS.add(produitDTO);
        }
        return produitDTOS;
    }



    @GetMapping("/getByType/{type_produit}")
    public List<ProduitDTO> getByType(@PathVariable String type_produit){
        List<Object[]> produits = produitService.getByType(type_produit);
        List<ProduitDTO> produitDTOS = new ArrayList<>();
        for (Object[] produit : produits) {
            ProduitDTO produitDTO = new ProduitDTO();
            produitDTO.setId_produit((Integer) produit[0]);
            produitDTO.setType_produit((String) produit[1]);
            if (produit[2] != null) {
                produitDTO.setQuantite(((Number) produit[2]).doubleValue());
            } else {
                produitDTO.setQuantite(null);
            }
            produitDTO.setQualite((Integer) produit[3]);
            produitDTO.setDate_prod((Date) produit[4]);
            produitDTOS.add(produitDTO);
        }
        return produitDTOS;
    }

    @GetMapping("/diagQ/{type_prod}")
    public List<DiagProDTO> diagQ(@PathVariable String type_prod){
        List<Object[]> produits =  produitService.diagQ(type_prod);
        List<DiagProDTO> produitDTOS = new ArrayList<>();
        for(Object[] produit : produits){
            DiagProDTO produitDTO = new DiagProDTO();
            if (produit[0] != null) {
                produitDTO.setQuantite(((Number) produit[0]).doubleValue());
            } else {
                produitDTO.setQuantite(null); // or handle it as appropriate
            }
            produitDTO.setQualite((Integer) produit[1]);
            produitDTO.setDate_prod((Date) produit[2]);
            produitDTOS.add(produitDTO);
        }
        return produitDTOS;
    }

}

