package com.whitesoft.dataimport.controller;

import com.whitesoft.dataimport.service.DataImportService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Наполняем БД данными по Отделениям обслуживания.
 * Created by tupichkindenis on 26.10.16.
 */
@Api(value = "users", description = "Endpoint for user management")
@RestController(value = "tooling")
public class DataImportController {

    @Autowired
    @Qualifier("ImportFromJsonDataFile")
    private DataImportService dataImportService;

    @ApiOperation(value = "Returns user details", notes = "Returns a complete list of users details with a date of last modification.", response = SecurityProperties.User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Niklas")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "User with given username does not exist"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
    @RequestMapping(value = "/populate", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity populate(){
        dataImportService.doImport();
        return ResponseEntity.ok().build();
    }

}
