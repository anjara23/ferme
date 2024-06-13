package com.example.graddle.Elevage.Controller;

import com.example.graddle.Elevage.DTO.KpiDTO;
import com.example.graddle.Elevage.Payload.ProductionRequest;
import com.example.graddle.Elevage.Repository.ProductionRepository;
import com.example.graddle.Elevage.Repository.ProduitRepository;
import com.example.graddle.Elevage.Services.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/elevage/production")
@RequiredArgsConstructor
public class ProductionController {

    private  final ProductionService productionService;
    private final ProductionRepository productionRepository;

    @GetMapping("/diagKPI")
   /* public Map<Integer, Double> diagKPI(@RequestBody ProductionRequest productionRequest){
        return productionService.diagKPI(productionRequest);
    }**/
    public List<KpiDTO> diagKPI(@RequestBody ProductionRequest productionRequest) {
        Integer id_produit = productionRequest.getId_produit();
        Integer mois = productionRequest.getMois();
        String type_produit = productionRequest.getType_produit();
        String espece = productionRequest.getEspece();

        List<Object[]> monthsAndKPIList = productionRepository.findMonthsAndKPI(mois, espece, type_produit, id_produit);
        List<KpiDTO> result = new ArrayList<>();

        for (Object[] entry : monthsAndKPIList) {
            String Mois = entry[0].toString(); // Utilisation de toString pour garantir un format String
            Double kpi = (Double) entry[1];
            result.add(new KpiDTO(Mois, kpi));
        }

        return result;
    }


}
