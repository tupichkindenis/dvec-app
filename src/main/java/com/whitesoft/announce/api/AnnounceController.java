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

    private final AnnounceService announceService;
    private final MessagingService messagingService;

    @Autowired
    public AnnounceController(AnnounceService service, MessagingService messagingService){
        this.messagingService = messagingService;
        this.announceService = service;
    }

    /**
     * @param param
     * @return
     */
    @RequestMapping(method   = RequestMethod.POST,
                    value    = "/announces/add",
                    consumes = "application/json; charset=UTF-8",
                    produces = "application/json; charset=UTF-8")
    public @ResponseBody ResponseEntity<?> add(@RequestBody AddAnnounceParam param) {

        // Добавляем объявление
        Announce announce = announceService.createAnnounce(param.getAuthor(), param.getHeader(), param.getText() );

        // Send notification
        messagingService.sendNotificationAboutNewAds(announce);

        // Prepare response
        Resource<Announce> resource = new Resource<>(announce);
        resource.add(linkTo(methodOn(AnnounceController.class).add(param)).withSelfRel());

        return ResponseEntity.ok(announce);
    }

}


