package com.whitesoft.messaging.services;

import com.google.gson.JsonObject;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.messaging.util.FCMHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("FCMessagingSerivce")
public class FCMessagingSerivce implements MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(FCMessagingSerivce.class);

    private FCMHelper fcmHelper;

    @Autowired
    public FCMessagingSerivce(FCMHelper fcmHelper){
        this.fcmHelper = fcmHelper;
    }

    @Override
    public void sendNotificationAboutNewAds(Announce ads) {

        JsonObject payLoad = new JsonObject();
        payLoad.addProperty("title",ads.getHeader());
        payLoad.addProperty("body", ads.getText());

        JsonObject data = new JsonObject();
        data.addProperty("id", ads.getId().toString() );

        logger.info(String.format("Notification: {%s}", payLoad.toString()));

        try{
            fcmHelper.sendTopicNotificationAndData("ads", payLoad, data);
        }catch (Exception ex)
        {
            logger.error(ex.getLocalizedMessage());
        }
    }
}
