package com.cochintravels.until.api;

import com.cochintravels.until.service.SheetToJsonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("sheets")
public class SheetApi {

    @Autowired
    private SheetToJsonService sheetToJsonService;

    @GetMapping(path = "/getSheetByID/{sheetName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSheetByID(@PathVariable String sheetName) {
        return sheetToJsonService.getSheetData(sheetName);
    }

    @GetMapping("/getRefreshDBStatus")
    public ResponseEntity<String> getRefreshDBStatus() {
        sheetToJsonService.refreshDB();
        return new ResponseEntity<>("Update DB with new Records", HttpStatus.CREATED);
    }
}
