package com.whitesoft;

import com.whitesoft.dataimport.service.DataImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by tupichkindenis on 16.11.16.
 */
@Component
public class StartupHouseKeeper {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupHouseKeeper.class);

    @Autowired
    @Qualifier("ImportFromJsonDataFile")
    private DataImportService dataImportService;


    @EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
        LOGGER.debug(" --- context refreshed --- ");

        // do database initialization
        dataImportService.doImport();
    }
}
