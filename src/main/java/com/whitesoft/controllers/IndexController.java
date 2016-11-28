package com.whitesoft.controllers;

import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.service.AnnounceService;
import com.whitesoft.messaging.services.FCMessagingSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private AnnounceService announceService;

    @PostMapping("/send-message")
    public String sendNotification(@Valid NotificationForm notificationForm, BindingResult bindingResult){

        logger.info(notificationForm.toString());

        if(bindingResult.hasErrors()){
            return "index";
        }
        return "index";
    }

    @RequestMapping("/")
    public String index(Model model){
        List<Announce> announces = announceService.getAll();
        model.addAttribute("announceList", announces);
        return "index";
    }
}
