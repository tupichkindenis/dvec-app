package com.whitesoft;

import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.model.AnnounceStatus;
import com.whitesoft.announce.repository.AnnounceRepository;
import com.whitesoft.announce.service.AnnounceService;
import com.whitesoft.domain.*;
import com.whitesoft.repository.BranchRepository;
import com.whitesoft.repository.OfficeRepository;
import com.whitesoft.repository.StationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DvecMobileServerApplicationTests {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private AnnounceRepository announceRepository;

	@Autowired
	@Qualifier("AnnounceServiceImpl")
	public AnnounceService announceService;

	@Test
	public void contextLoads() {

//		// Add branch
//		Branch branch = new Branch(UUID.randomUUID(), "my branch");
//		branchRepository.save(branch);
//
//		// Add office
//		Office office = new Office(UUID.randomUUID(), "my office", branch);
//		officeRepository.save(office);
//
//		// Add Station
//		LegalAddress legalAddress = new LegalAddress("index", "region", "district", "settlement", "street", "building", "room");
//		GeoLocation geoLocation = new GeoLocation(0, 0);
//		Station station = new Station("my 1st station", office, legalAddress, geoLocation);
//
//		stationRepository.save(station);
//
//		assertThat(branchRepository.count()).isEqualTo(1);
//		assertThat(branchRepository.findByName("my branch").size()).isEqualTo(1);
//		assertThat(branchRepository.findByName("my branch").get(0).getOffices().size()).isEqualTo(1);
//		assertThat(officeRepository.count()).isEqualTo(1);
//		assertThat(stationRepository.count()).isEqualTo(1);
	}


	/**
	 * Тест получения одного объявления по его идентификатору.
     */
	@Test
	public void testGetAnnouceById(){

	}

	/**
	 * Тест первичного получения объявлений.
	 * 1) Устройство запрашивает N-последних объявлений.
	 *
	 * 2) Система возвращает N-объявлений (но не более 100) начиная с самого последненго.
	 *    Объявления отсортированы по дате создания в обратном порядке.
     */

	/**
	 * Тест получения списка объявлений.
	 * Получение обновлений.
	 * Получаем записи новее самой новой которая имеется на устройстве.
	 * Сколько бы не запросили, получаем мксимум 100 записей.
     */

	/**
	 * Тест получения списка объявлений.
	 * Получение страницы №2.
	 */

	/**
	 * Тест получения списка объявлений.
	 * Получение страницы №3.
	 */

}
