package com.cochintravels.until.service;

import com.cochintravels.until.model.SheetJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SheetToJsonService {

    @Value("${cochintravels.sheet.url}")
    private String SHEET_URL;

    @Value("${cochintravels.sheet.count}")
    private int SHEET_COUNT;

    @Autowired
    SheetRepository repo;

    @Autowired
    @Qualifier("sheetServiceTemplate")
    RestTemplate restTemplate;

    public String getSheetData(String sheetName){
        Optional<SheetJson> sheetJsonOptional = repo.findById(sheetName);
        if(sheetJsonOptional.isPresent()){
            return sheetJsonOptional.get().getContent();
        }
        return "";
    }

    public void refreshDB(){
        ArrayList<SheetJson> list=new ArrayList();
        for (int i = 1; i <= SHEET_COUNT; i++) {
            String sheetName = "Sheet"+i;
            ResponseEntity<String> response = restTemplate.getForEntity(SHEET_URL+sheetName, String.class);
            String jsonBody = response.getBody();
            list.add(new SheetJson(sheetName,jsonBody));
        }
        repo.saveAll(list);
    }

    @Bean
    @Qualifier("sheetServiceTemplate")
    public RestTemplate sheetRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
