package com.whitesoft.dataimport.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.whitesoft.dataimport.objects.BranchObject;
import com.whitesoft.dataimport.objects.OfficeObject;
import com.whitesoft.dataimport.objects.StationObject;
import com.whitesoft.domain.*;
import com.whitesoft.domain.enums.Day;
import com.whitesoft.domain.enums.PaymentSystems;
import com.whitesoft.repository.BranchRepository;
import com.whitesoft.repository.OfficeRepository;
import com.whitesoft.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * Created by tupichkindenis on 26.10.16.
 */
@Service("ImportFromJsonDataFile")
public class ImportFromJsonDataFile implements DataImportService {

    private static final Logger logger = LoggerFactory.getLogger(ImportFromJsonDataFile.class);

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public void doImport() {

        Gson gson = new Gson();

        ClassLoader cl = this.getClass().getClassLoader();

        InputStream inputStream = cl.getResourceAsStream("stations.json");

        Collection<StationObject> stations = null;

        Type collectionType = new TypeToken<Collection<StationObject>>(){}.getType();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(inputStream));
        stations = gson.fromJson(reader, collectionType);

        Map<BranchObject,List<StationObject>> byBranch = stations.stream().collect(Collectors.groupingBy(StationObject::getBranch));

        System.out.println("");

        // Необходимо заполнить репозиторий филиалов (Branches)
        //
        byBranch.forEach((k,v)->{

            // k - филиал
            // v - список отделений данного филиала
            logger.info("Branch :" + k.getName() + ", offices amount :" + v.size() );

            // create branch entity
            // Идентификатор филиала должен быть совместим с идентификаторами ДЭК.
            Branch branchEntity = Branch.builder().id(k.getId()).name(k.getName()).build();
            branchRepository.save(branchEntity);

            Map<OfficeObject,List<StationObject>> byOffice =
                    v.stream().filter(p->k.equals(p.getBranch())).collect(Collectors.groupingBy(StationObject::getOffice));

            byOffice.forEach((ok,ov)->{
                // ok - отделение
                // ov - список станций данного отделения
                System.out.println(" - office :" + ok.getName() + ", stations amount :" + ov.size() );

                Office officeEntity = Office.builder()
                        .id(ok.getId())
                        .name(ok.getName())
                        .branch(branchEntity)
                        .paySystems( new ArrayList<PaymentSystem>(){{
                            // create identifier for payment system now it is only CHRONOPAY
                            add( PaymentSystem.builder()
                                    .type(PaymentSystems.CHRONOPAY)
                                    .prefix(ov.get(0).getPayment().getChronopay().getBranchid())
                                    .suffix(ov.get(0).getPayment().getChronopay().getOfficeid().toString()).build() );
                        }})
                        .build();

                officeRepository.save(officeEntity);

                ov.forEach( stationObject -> {

                    System.out.println(" - - station :" + stationObject.getName() );

                    //
                    Station stationEntity = new Station(
                            stationObject.getName(),
                            officeEntity,
                            new LegalAddress(
                                    stationObject.getAddress().getIndex(),
                                    stationObject.getAddress().getRegion(),
                                    stationObject.getAddress().getDistrict(),
                                    stationObject.getAddress().getSettlement(),
                                    stationObject.getAddress().getStreet(),
                                    stationObject.getAddress().getBuilding(),
                                    stationObject.getAddress().getRoom()),
                            new GeoLocation(
                                    stationObject.getLocation().getLatitude(),
                                    stationObject.getLocation().getLongitude()
                            ));

                    // Инициализируем информацию о расписании работы
                    // удаляе "пустые дни"
                    stationObject.getSchedule()
                            .stream()
                            .filter(p -> !("-".equals(p.getWorktime())) || !("-".equals(p.getDinnertime())))
                            .collect( Collectors.toList() )
                            .forEach( d ->
                                stationEntity.getWorkDays().add(
                                    new Workday(Day.valueOf(d.getName().toUpperCase()), d.getWorktime(), d.getDinnertime()) ) );

                    // Инициализируем телефоны
                    stationObject.getPhones()
                            .stream()
                            .filter(p->!"-".equals(p))
                            .collect(Collectors.toList())
                            .forEach( p ->
                        stationEntity.getPhones().add( new PhoneNumber(p) ) );

                    // Инициализируем факсы
                    stationObject.getFaxes()
                            .stream()
                            .filter(p->!"-".equals(p))
                            .collect(Collectors.toList())
                            .forEach( f ->
                        stationEntity.getFaxes().add( new FaxNumber(f)));

                    // Инициализируем адреса почтовые
                    stationObject.getEmails()
                            .stream()
                            .filter(p -> p != null)
                            .filter(p -> !p.isEmpty())
                            .collect(Collectors.toList())
                            .forEach( e ->
                                stationEntity.getEmails().add( new EmailAddress(e) ) );

                    stationRepository.save(stationEntity);
                });
            });
        });
        System.out.println(" - count of records - " + stationRepository.count());
    }
}
