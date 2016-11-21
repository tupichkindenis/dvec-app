package com.whitesoft;

import com.whitesoft.announce.service.AnnounceService;
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

    @Autowired
    @Qualifier("AnnounceServiceImpl")
    private AnnounceService announceService;


    @EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
        LOGGER.debug(" --- context refreshed --- ");

        // do database initialization
        dataImportService.doImport();

        // initialize for test announces
        // TODO: 21.11.16 remove on production
        for (int i = 0; i < 100; i++) {
            announceService.createAnnounce(
                    String.format("Author of %d announce.", i),
                    String.format("Announce #%d",i),
                    "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum."
            );
        }
    }
}
