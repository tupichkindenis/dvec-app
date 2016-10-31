package com.whitesoft;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.whitesoft.dataimport.objects.BranchObject;
import com.whitesoft.dataimport.objects.OfficeObject;
import com.whitesoft.dataimport.objects.StationObject;
import com.whitesoft.domain.*;
import com.whitesoft.repository.BranchRepository;
import com.whitesoft.repository.OfficeRepository;
import com.whitesoft.repository.StationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

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

	@Test
	public void contextLoads() {

		// Add branch
		Branch branch = new Branch(UUID.randomUUID(), "my branch");
		branchRepository.save(branch);

		// Add office
		Office office = new Office(UUID.randomUUID(), "my office", branch);
		officeRepository.save(office);

		// Add Station
		LegalAddress legalAddress = new LegalAddress("index","region","district","settlement","street","building","room");
		GeoLocation geoLocation = new GeoLocation(0,0);
		Station station = new Station("my 1st station", office, legalAddress,geoLocation);

		stationRepository.save(station);

		assertThat(branchRepository.count()).isEqualTo(1);
		assertThat(branchRepository.findByName("my branch").size()).isEqualTo(1);
		assertThat(branchRepository.findByName("my branch").get(0).getOffices().size()).isEqualTo(1);
		assertThat(officeRepository.count()).isEqualTo(1);
		assertThat(stationRepository.count()).isEqualTo(1);
	}
}
