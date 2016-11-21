package com.whitesoft.messaging.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.sun.javafx.binding.StringFormatter;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.messaging.util.FCMHelper;
import com.whitesoft.messaging.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.whitesoft.messaging.util.FCMHelper.TYPE_TO;

@Service("FCMessagingSerivce")
public class FCMessagingSerivce implements MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(FCMessagingSerivce.class);

    @Override
    public void sendNotificationAboutNewAds(Announce ads) {

        JsonObject payLoad = new JsonObject();
        payLoad.addProperty("id", ads.getId().toString() );
        payLoad.addProperty("title",ads.getHeader());
        payLoad.addProperty("body", ads.getText());

        logger.info(StringFormatter.format("Notification: {%s}", payLoad.toString()).getValue());

        try{
            FCMHelper fcmHelper = FCMHelper.getInstance();
            fcmHelper.sendNotification(TYPE_TO,"/topics/ads", payLoad);
        }catch (Exception ex)
        {
            logger.error(ex.getLocalizedMessage());
        }
    }
}
