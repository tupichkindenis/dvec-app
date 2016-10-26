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
		Branch branch = new Branch("my branch");
		branchRepository.save(branch);

		// Add office
		Office office = new Office("my office", branch);
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

	@Test
	public void gsonConversation() throws IOException {
		Gson gson = new Gson();

		Type collectionType = new TypeToken<Collection<StationObject>>(){}.getType();
		Resource resource = new ClassPathResource("stations.json");
		Collection<StationObject> stations = gson.fromJson(new FileReader(resource.getFile()), collectionType);

		Map<BranchObject,List<StationObject>> byBranch = stations.stream().collect(Collectors.groupingBy(StationObject::getBranch));


		System.out.println("");
		// Необходимо заполнить репозиторий филиалов (Branches)
		//
		byBranch.forEach((k,v)->{
			// k - филиал
			// v - список отделений данного филиала
			System.out.println("Branch :" + k.getName() + ", offices amount :" + v.size() );

			// create branch entity
			Branch branchEntity = new Branch(k.getName());
			branchRepository.save(branchEntity);

			Map<OfficeObject,List<StationObject>> byOffice =
					v.stream().filter(p->k.equals(p.getBranch())).collect(Collectors.groupingBy(StationObject::getOffice));

			byOffice.forEach((ok,ov)->{
				// ok - отделение
				// ov - список станций данного отделения
				System.out.println(" - office :" + ok.getName() + ", stations amount :" + ov.size() );

				Office officeEntity = new Office(ok.getName(),branchEntity);
				officeRepository.save(officeEntity);

				ov.forEach(stationObject -> {
					System.out.println(" - - station :" + stationObject.getName() );

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
									stationObject.getLocation().getLatitude()
							));
					stationRepository.save(stationEntity);
				});
			});
		});
		System.out.println(" - count of records - " + stationRepository.count());
	}
}
