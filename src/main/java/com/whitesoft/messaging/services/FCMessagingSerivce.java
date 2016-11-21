package com.whitesoft.messaging.services;

import com.google.gson.JsonObject;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.messaging.util.FCMHelper;
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
        payLoad.addProperty("title",ads.getHeader());
        payLoad.addProperty("body", ads.getText());

        JsonObject data = new JsonObject();
        data.addProperty("id", ads.getId().toString() );

        logger.info(String.format("Notification: {%s}", payLoad.toString()));

        try{
            FCMHelper fcmHelper = FCMHelper.getInstance();
//            fcmHelper.sendNotifictaionAndData(TYPE_TO, "topics/ads", payLoad, data);
//            fcmHelper.sendTopicNotification("ads", payLoad);
            fcmHelper.sendTopicNotificationAndData("ads", payLoad, data);
        }catch (Exception ex)
        {
            logger.error(ex.getLocalizedMessage());
        }
    }
}
