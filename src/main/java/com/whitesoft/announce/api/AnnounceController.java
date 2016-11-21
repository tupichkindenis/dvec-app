package com.whitesoft.announce.api;

import com.whitesoft.announce.api.dto.AddAnnounceParam;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.repository.AnnounceRepository;
import com.whitesoft.announce.service.AnnounceService;
import com.whitesoft.messaging.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RepositoryRestController
public class AnnounceController {

    private final AnnounceRepository announceRepository;
    private final AnnounceService announceService;

    @Autowired
    @Qualifier("FCMessagingSerivce")
    public MessagingService messagingService;

    @Autowired
    public AnnounceController(AnnounceRepository repo, AnnounceService service){
        this.announceRepository = repo;
        this.announceService = service;
    }


//    /**
//     * @param id
//     * @param id
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST, value = "/announces/add")
//    public @ResponseBody ResponseEntity<?> getById(/*@PathVariable(value = "id") UUID id*/){
//
//        UUID id = UUID.randomUUID();
//
//        Announce announce = announceRepository.findOne(id);
//
//        Resource<Announce> resource = new Resource<>(announce);
//
//        resource.add(linkTo(methodOn(AnnounceController.class).getById()).withSelfRel());
//
//        return ResponseEntity.ok(resource);
//    }

    /**
     * Add new Anounce as DRAFT.
     * @param param
     * @return
     */
    @RequestMapping(value    = "/announces/add",
                    method   = RequestMethod.POST,
                    consumes = "application/json; charset=UTF-8",
                    produces = "application/json; charset=UTF-8")
    public @ResponseBody ResponseEntity<?> add(@RequestBody AddAnnounceParam param) {

        // Add announce
        Announce announce = announceService.createAnnounce(param.getAuthor(),param.getHeader(),param.getText());

        // Send notification
        messagingService.sendNotificationAboutNewAds(announce);

        // prepare responce
        Resource<Announce> resource = new Resource<>(announce);

        resource.add(linkTo(methodOn(AnnounceController.class).add(param)).withSelfRel());

        return ResponseEntity.ok(announce);
    }
//
//    /**
//     * Publish an Announce from DRAFT or REVIEW statuses.
//     * @return
//     */
//    @RequestMapping(value = "/publish", method = RequestMethod.POST)
//    public ResponseEntity<?> publish(@RequestBody PublishAnnounceParam param)
//    {
////        AnnounceDTO announceDTO = new AnnounceDTO();
////        announceDTO.set
////        Announce announce = announceRepository.findOne(param.getId());
////
////        if (announce == null){
////            // todo throw exception 404
////        }
////
////        if (announce.getStatus() == AnnounceStatus.PUBLISHED){
////            // todo throw already published
////        }
////
////        announce.setPublicationDate(new Date());
////        announce.setStatus(AnnounceStatus.PUBLISHED);
////
////        return ResponseEntity.ok(announceRepository.save(announce));
//        return ResponseEntity.ok().build();
//
//    }
//
//
//
//    /**
//     * Необходимо получить все записи опубликованные до даты XXXX,
//     * но не позднее даты УУУУУ,
//     * количество записей не должно превышать БББ.
//     * Записи должны быть отсортированы по дате публикации
//     * если дата ХХХХ не указана, то используем текущую дату
//     * если дата YYYY не указана, используем (ХХХХ - 30 дней)
//     *
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseEntity<?> list(
//                                 @RequestParam(required = false, defaultValue = "15") Integer count
//                                ,@RequestParam(required = false) Date until
//                                ,@RequestParam(required = false) Long since_id
//                                ,@RequestParam(required = false) Long max_id){
//        return ResponseEntity.ok(
//                new AnnounceListDTO(announceService.getAll()
//                        .stream()
//                        .map(AnnounceMapper.INSTANCE::announceToAnnounceDto)
//                        .collect(Collectors.toList()))
//                                );
//    }
//
//
//    @RequestMapping(value = "/populate", method = RequestMethod.POST)
//    public ResponseEntity populate(){
//        for (int i = 0; i < 10 ; i++) {
//            add(new AddAnnounceParam("author","header","text"));
//        }
//        return ResponseEntity.ok().build();
//    }

}


