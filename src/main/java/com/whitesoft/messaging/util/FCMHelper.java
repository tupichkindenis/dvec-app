package com.whitesoft.messaging.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.whitesoft.messaging.services.FCMessagingSerivce;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by Toni on 30.09.2016.
 */
@Component
public class FCMHelper  {

    private static final Logger logger = LoggerFactory.getLogger(FCMHelper.class);

    private final ContentType contentType = ContentType.APPLICATION_JSON;


    public static final String TYPE_TO = "to";  // Use for single devices, device groups and topics
    public static final String TYPE_CONDITION = "condition"; // Use for Conditions

    private final FCMProperties config;

    @Autowired
    private FCMHelper(FCMProperties config) {
        this.config = config;
    }

    /**
     * Send notification
     * @param type
     * @param typeParameter
     * @param notificationObject
     * @return
     * @throws IOException
     */
    public String sendNotification(String type, String typeParameter, JsonObject notificationObject) throws IOException {
        return sendNotifictaionAndData(type, typeParameter, notificationObject, null);
    }

    /**
     * Send data
     * @param type
     * @param typeParameter
     * @param dataObject
     * @return
     * @throws IOException
     */
    public String sendData(String type, String typeParameter, JsonObject dataObject) throws IOException {
        return sendNotifictaionAndData(type, typeParameter, null, dataObject);
    }

    /**
     * Send notification and data
     * @param type
     * @param typeParameter
     * @param notificationObject
     * @param dataObject
     * @return
     * @throws IOException
     */
    public String sendNotifictaionAndData(String type, String typeParameter, JsonObject notificationObject, JsonObject dataObject) throws IOException {
        String result = null;
        if (type.equals(TYPE_TO) || type.equals(TYPE_CONDITION)) {
            JsonObject sendObject = new JsonObject();
            sendObject.addProperty(type, typeParameter);
            result = sendFcmMessage(sendObject, notificationObject, dataObject);
        }
        return result;
    }

    /**
     * Send data to a topic
     * @param topic
     * @param dataObject
     * @return
     * @throws IOException
     */
    public String sendTopicData(String topic, JsonObject dataObject) throws IOException{
        return sendData(TYPE_TO, "/topics/" + topic, dataObject);
    }

    /**
     * Send notification to a topic
     * @param topic
     * @param notificationObject
     * @return
     * @throws IOException
     */
    public String sendTopicNotification(String topic, JsonObject notificationObject) throws IOException{
        return sendNotification(TYPE_TO, "/topics/" + topic, notificationObject);
    }

    /**
     * Send notification and data to a topic
     * @param topic
     * @param notificationObject
     * @param dataObject
     * @return
     * @throws IOException
     */
    public String sendTopicNotificationAndData(String topic, JsonObject notificationObject, JsonObject dataObject) throws IOException{
        return sendNotifictaionAndData(TYPE_TO, "/topics/" + topic, notificationObject, dataObject);
    }

    /**
     * Send a Firebase Cloud Message
     * @param sendObject - Contains to or condition
     * @param notificationObject - PushNotification Data
     * @param dataObject - Data
     * @return
     * @throws IOException
     */
    private String sendFcmMessage(JsonObject sendObject, JsonObject notificationObject, JsonObject dataObject) throws IOException {

        HttpPost httpPost = new HttpPost(config.getUrl());

        // Header set
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "key=" + config.getServerKey());

        if (notificationObject != null) sendObject.add("notification", notificationObject);
        if (dataObject != null) sendObject.add("data", dataObject);

        String data = URLDecoder.decode(sendObject.toString(),"UTF-8");

        StringEntity entity = new StringEntity(data);
        entity.setContentType(contentType.getMimeType());

        // JSON-Object
        httpPost.setEntity(entity);

        HttpClient httpClient = HttpClientBuilder.create().build();

        BasicResponseHandler responseHandler = new BasicResponseHandler();
        String response = (String) httpClient.execute(httpPost, responseHandler);

        logger.info(data);
        logger.info(response);

        return response;
    }

}