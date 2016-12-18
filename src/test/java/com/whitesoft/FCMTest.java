package com.whitesoft;

import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.JsonObject;
import com.whitesoft.announce.model.Announce;
import com.whitesoft.messaging.model.PushNotification;
import com.whitesoft.messaging.services.FCMessagingSerivce;
import com.whitesoft.messaging.util.FCMHelper;
import com.whitesoft.messaging.util.JsonUtil;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static com.whitesoft.messaging.util.FCMHelper.TYPE_TO;

/**
 * Created by tupichkindenis on 18.11.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FCMTest {

    private static final Logger logger = LoggerFactory.getLogger(FCMHelper.class);

    private final ContentType contentType = ContentType.APPLICATION_JSON;

    private final String key = "AIzaSyCPknQdIBAHeRdHFAqBScSp2cg80CkmPSM";
    private final String requestUrl = "https://fcm.googleapis.com/fcm/send";


    @Autowired
    private FCMessagingSerivce fcMessagingSerivce;

    @Autowired
    private FCMHelper fcmHelper;

//    @Test
//    public void testSendMessage() throws IOException {
//
//        Announce announce = new Announce();
//        announce.setHeader("Заголовок");
//        announce.setAuthor("Автор");
//        announce.setText("Текст");
//
//        fcMessagingSerivce.sendNotificationAboutNewAds(announce);
//    }

    @Test
    public void testSendMessageLikeGolos(){

        PushNotification notification = new PushNotification("/topics/ads", UUID.randomUUID(), new Date(), "Заголовок", "Тельце");

        try {

            HttpClient httpClient = HttpClientBuilder.create().build();

            String body_json = JsonUtil.toJson(notification);
            StringEntity body = new StringEntity(body_json != null ? body_json : "");
            body.setContentType(contentType.getMimeType());

            HttpResponse response = httpClient.execute(RequestBuilder.post(requestUrl).addHeader(HttpHeaders.AUTHORIZATION, "key=" + key).setEntity(body).build());

            if (response.getStatusLine().getStatusCode() != HttpStatusCodes.STATUS_CODE_OK)
                logger.error(String.format("Статус: %d, , уведомление не отправлено по причине: %s", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase()));

        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
