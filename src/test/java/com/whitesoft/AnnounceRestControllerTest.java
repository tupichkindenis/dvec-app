package com.whitesoft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whitesoft.announce.api.AnnounceController;
import com.whitesoft.announce.api.dto.AddAnnounceParam;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.model.AnnounceStatus;
import com.whitesoft.announce.service.AnnounceService;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tupichkindenis on 13.11.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DvecMobileServerApplication.class)
public class AnnounceRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @InjectMocks
    private AnnounceController announceController;

    @Mock
    @Qualifier("AnnounceServiceImpl")
    private AnnounceService announceService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final UUID   adsId     = UUID.randomUUID();
    private final String adsAuthor = "Author";
    private final String adsHeader = "Header";
    private final String adsText   = "Text";
    private final AnnounceStatus adsStatus = AnnounceStatus.PUBLISHED;
    private final Date adsCreateTime = new Date();

    // todo Add id to ctor.
    private final Announce announce = (new Announce()).builder()
            .status(adsStatus)
            .createTime(adsCreateTime)
            .author(adsAuthor)
            .header(adsHeader)
            .text(adsText).build();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(announceController).build();
        when(announceService.createAnnounce(any(),any(),any())).thenReturn(announce);
    }

    /**
     * Test that Ads is successfully added.
     */
    @Test
    public void postTest_AddAdsSuccess() throws Exception {
        AddAnnounceParam addAnnounceParam = new AddAnnounceParam();
        addAnnounceParam.setAuthor(adsAuthor);
        addAnnounceParam.setHeader(adsHeader);
        addAnnounceParam.setText(adsText);

        this.mockMvc.perform(post("/announces/add")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(addAnnounceParam)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest_DeleteAdsSuccess(){
    }

    /**
     * Проверка на получение существующего объявления.
     * @throws Exception
     */
    @Test
    public void getTest_GetAdsByIdSuccess() throws Exception {

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        // Prepare
        announce.setId(adsId);

        // Arrange
        when(announceService.getById(adsId)).thenReturn(Optional.of(announce));

        // Act
        mockMvc.perform(get("/announces/{id}", adsId.toString()))
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",         is(adsId.toString())))
                .andExpect(jsonPath("$.createTime", is((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(adsCreateTime))))
                .andExpect(jsonPath("$.author",     is(adsAuthor)))
                .andExpect(jsonPath("$.header",     is(adsHeader)))
                .andExpect(jsonPath("$.text",       is(adsText)))
        ;

        verify(announceService).getById(uuidArgumentCaptor.capture());
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(uuidArgumentCaptor.getValue()).isEqualTo(adsId);
        assertions.assertAll();
    }

    // Проверка на получение не существующего объявления.
    @Test
    public void getTest_GetAdsById_IdDoesNotExist() throws Exception {

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        // Arrange
        when(announceService.getById(adsId)).thenReturn(Optional.empty());
        // Act
        mockMvc.perform(get("/announces/{id}", adsId.toString()))
        // Assert
        .andExpect(status().isNotFound());

        verify(announceService).getById(uuidArgumentCaptor.capture());
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(uuidArgumentCaptor.getValue()).isEqualTo(adsId);
        assertions.assertAll();


    }


    // Проверка получения пустого списка объявлений (без параметров)
    @Test
    public void getTest_GetEmptyListOfAdsWithoutParametersSuccess() throws Exception {

        // Arrange
        final List<Announce> announces = new ArrayList<>();
        when(announceService.getAll()).thenReturn(announces);

        // Act
        this.mockMvc.perform(get("/announces/list")
                                .contentType(APPLICATION_JSON_UTF8)
                                .param("since-date","")
                                .param("until-date","")
                                .param("count",""))
                // -- Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.count", is(announces.size())))
                .andExpect(jsonPath("$.items", empty()));
    }

    // Проверка получения не пустого списка объявлений (без параметров)
    @Test
    public void getTest_GetListOfAdsWithoutParametersSuccess() throws Exception {

        // Arrange
        List<Announce> announces = new ArrayList<>();

        // -- test announce #1
        Announce firstAnnounce = new Announce();
        firstAnnounce.setId(UUID.randomUUID());
        firstAnnounce.setAuthor(adsAuthor);
        firstAnnounce.setHeader(adsHeader);
        firstAnnounce.setText(adsText);
        firstAnnounce.setStatus(AnnounceStatus.PUBLISHED);
        //
        announces.add(firstAnnounce);

        // -- test announce #2
        Announce secondAnnounce = new Announce();
        secondAnnounce.setId(UUID.randomUUID());
        secondAnnounce.setAuthor(adsAuthor);
        secondAnnounce.setHeader(adsHeader);
        secondAnnounce.setText(adsText);
        secondAnnounce.setStatus(AnnounceStatus.PUBLISHED);
        //
        announces.add(secondAnnounce);

        when(announceService.getAll()).thenReturn(announces);

        // Act
        this.mockMvc.perform(get("/announces/list")
                .contentType(APPLICATION_JSON_UTF8)
                .param("since-date","")
                .param("until-date","")
                .param("count",""))
                // -- Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.count",               is(announces.size())))
                .andExpect(jsonPath("$.items[0].id",         is(firstAnnounce.getId().toString())))
                .andExpect(jsonPath("$.items[0].createTime", is((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(firstAnnounce.getCreateTime()))))
                .andExpect(jsonPath("$.items[0].author",     is(firstAnnounce.getAuthor())))
                .andExpect(jsonPath("$.items[0].header",     is(firstAnnounce.getHeader())))
                .andExpect(jsonPath("$.items[0].text",       is(firstAnnounce.getText())))
                .andExpect(jsonPath("$.items[1].id",         is(secondAnnounce.getId().toString())))
                .andExpect(jsonPath("$.items[1].createTime", is((new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")).format(secondAnnounce.getCreateTime()))))
                .andExpect(jsonPath("$.items[1].author",     is(secondAnnounce.getAuthor())))
                .andExpect(jsonPath("$.items[1].header",     is(secondAnnounce.getHeader())))
                .andExpect(jsonPath("$.items[1].text",       is(secondAnnounce.getText())))
        ;
    }
}
