package com.whitesoft.messaging.services;

import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.JsonObject;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.messaging.model.PushNotification;
import com.whitesoft.messaging.util.FCMHelper;
import com.whitesoft.messaging.util.JsonUtil;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("FCMessagingSerivce")
public class FCMessagingSerivce implements MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(FCMessagingSerivce.class);

    private final String key = "AIzaSyCPknQdIBAHeRdHFAqBScSp2cg80CkmPSM";
    private final String requestUrl = "https://fcm.googleapis.com/fcm/send";

    private FCMHelper fcmHelper;

    @Autowired
    public FCMessagingSerivce(FCMHelper fcmHelper){
        this.fcmHelper = fcmHelper;
    }

    @Override
    public void sendNotificationAboutNewAds(Announce ads) {

        PushNotification notification = new PushNotification("/topics/ads"
                ,ads.getId()
                ,ads.getHeader()
                ,ads.getText());

        try {

            HttpClient httpClient = HttpClientBuilder.create().build();

            String body_json = JsonUtil.toJson(notification);
            StringEntity body = new StringEntity(body_json != null ? body_json : "");
            body.setContentType(ContentType.APPLICATION_JSON.getMimeType());

            HttpResponse response = httpClient.execute(RequestBuilder.post(requestUrl).addHeader(HttpHeaders.AUTHORIZATION, "key=" + key).setEntity(body).build());

            if (response.getStatusLine().getStatusCode() != HttpStatusCodes.STATUS_CODE_OK) {
                logger.error(String.format("Статус: %d, , уведомление не отправлено по причине: %s",
                        response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase()));
            }

        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
