package com.whitesoft.announce.api;

import com.whitesoft.announce.api.dto.AddAnnounceParam;
import com.whitesoft.announce.api.dto.AnnounceDTO;
import com.whitesoft.announce.api.dto.AnnounceListDTO;
import com.whitesoft.announce.api.dto.PublishAnnounceParam;
import com.whitesoft.announce.api.mappers.AnnounceMapper;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.model.AnnounceStatus;
import com.whitesoft.announce.repository.AnnounceRepository;
import com.whitesoft.announce.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 *
 */
@RestController
@RequestMapping("/announces")
public class AnnounceController {

    @Autowired
    private AnnounceRepository announceRepository;

    @Autowired
    @Qualifier("AnnounceServiceImpl")
    private AnnounceService announceService;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain")
    public String test(@RequestParam(required = false, defaultValue = "empty") String name){
        return "Hello " + name;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value    = "/{id}",
                    method   = RequestMethod.GET,
                    produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getById(@PathVariable(value = "id") UUID id){
        return announceService.getById(id)
                .map(AnnounceMapper.INSTANCE::announceToAnnounceDto)
                .map(announceDTO -> new ResponseEntity<>(announceDTO, OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    /**
     * Add new Anounce as DRAFT.
     * @param param
     * @return
     */
    @RequestMapping(value    = "/add",
                    method   = RequestMethod.POST,
                    consumes = "application/json; charset=UTF-8",
                    produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> add(@RequestBody AddAnnounceParam param) {
        Announce announce = announceService.createAnnounce(param.getAuthor(),param.getHeader(),param.getText());
        return ResponseEntity.ok(announce);
    }

    /**
     * Publish an Announce from DRAFT or REVIEW statuses.
     * @return
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity<?> publish(@RequestBody PublishAnnounceParam param)
    {
//        AnnounceDTO announceDTO = new AnnounceDTO();
//        announceDTO.set
//        Announce announce = announceRepository.findOne(param.getId());
//
//        if (announce == null){
//            // todo throw exception 404
//        }
//
//        if (announce.getStatus() == AnnounceStatus.PUBLISHED){
//            // todo throw already published
//        }
//
//        announce.setPublicationDate(new Date());
//        announce.setStatus(AnnounceStatus.PUBLISHED);
//
//        return ResponseEntity.ok(announceRepository.save(announce));
        return ResponseEntity.ok().build();

    }



    /**
     * Необходимо получить все записи опубликованные до даты XXXX,
     * но не позднее даты УУУУУ,
     * количество записей не должно превышать БББ.
     * Записи должны быть отсортированы по дате публикации
     * если дата ХХХХ не указана, то используем текущую дату
     * если дата YYYY не указана, используем (ХХХХ - 30 дней)
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(
                                 @RequestParam(required = false, defaultValue = "15") Integer count
                                ,@RequestParam(required = false) Date until
                                ,@RequestParam(required = false) Long since_id
                                ,@RequestParam(required = false) Long max_id){
        return ResponseEntity.ok(
                new AnnounceListDTO(announceService.getAll()
                        .stream()
                        .map(AnnounceMapper.INSTANCE::announceToAnnounceDto)
                        .collect(Collectors.toList()))
                                );
    }


    @RequestMapping(value = "/populate", method = RequestMethod.POST)
    public ResponseEntity populate(){
        for (int i = 0; i < 10 ; i++) {
            add(new AddAnnounceParam("author","header","text"));
        }
        return ResponseEntity.ok().build();
    }

}


