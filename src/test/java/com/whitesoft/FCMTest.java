package com.whitesoft;

import com.google.gson.JsonObject;
import com.whitesoft.messaging.util.FCMHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.whitesoft.messaging.util.FCMHelper.TYPE_TO;

/**
 * Created by tupichkindenis on 18.11.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FCMTest {
    @Autowired
    private FCMHelper fcmHelper;

    @Test
    public void testSendMessage() throws IOException {

        JsonObject payLoad = new JsonObject();
        payLoad.addProperty("title","notification title");
        payLoad.addProperty("body","notification body");

        fcmHelper.sendNotification(TYPE_TO,"/topics/ads",payLoad);
    }
}
