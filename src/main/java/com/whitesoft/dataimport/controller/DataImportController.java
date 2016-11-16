package com.whitesoft.dataimport.controller;

import com.whitesoft.dataimport.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Наполняем БД данными по Отделениям обслуживания.
 * Created by tupichkindenis on 26.10.16.
 */
@RestController
public class DataImportController {

    @Autowired
    @Qualifier("ImportFromJsonDataFile")
    private DataImportService dataImportService;

    @RequestMapping(value = "/populate", method = RequestMethod.POST)
    @Deprecated
    public ResponseEntity populate(){
        dataImportService.doImport();
        return ResponseEntity.ok().build();
    }

}
