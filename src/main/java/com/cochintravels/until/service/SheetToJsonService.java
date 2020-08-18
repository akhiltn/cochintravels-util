package com.cochintravels.until.service;

import com.cochintravels.until.model.SheetJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SheetToJsonService {

    @Autowired
    SheetRepository repo;
    @Autowired
    RestTemplate restTemplate;
    @Value("${cochintravels.sheet.url}")
    private String SHEET_URL;
    @Value("${cochintravels.sheet.count}")
    private int SHEET_COUNT;

    public String getSheetData(String sheetName) {
        Optional<SheetJson> sheetJsonOptional = repo.findById(sheetName);
        if (sheetJsonOptional.isPresent()) {
            return sheetJsonOptional.get().getContent();
        }
        return "";
    }

    public void refreshDB() {
        ArrayList<SheetJson> list = new ArrayList();
        for (int i = 1; i <= SHEET_COUNT; i++) {
            String sheetName = "Sheet" + i;
            ResponseEntity<String> response = restTemplate.getForEntity(SHEET_URL + sheetName, String.class);
            String jsonBody = response.getBody();
            list.add(new SheetJson(sheetName, jsonBody));
        }
        repo.saveAll(list);
    }

}
