package com.whitesoft.messaging.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="fcm")
public class FCMProperties {

    /**
     * Google URL to use firebase cloud messenging
     */
    private String url;

    /**
     * Your SECRET server key
     */
    private String serverKey;
}
