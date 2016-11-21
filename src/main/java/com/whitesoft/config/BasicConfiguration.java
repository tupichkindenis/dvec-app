package com.whitesoft.config;

import com.whitesoft.messaging.util.FCMProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tupichkindenis on 21.11.16.
 */
@Configuration
@EnableConfigurationProperties(FCMProperties.class)
public class BasicConfiguration {
}
