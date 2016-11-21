package com.whitesoft.messaging.services;

import com.whitesoft.announce.model.Announce;

/**
 * Created by tupichkindenis on 21.11.16.
 */
public interface MessagingService {
    void sendNotificationAboutNewAds(Announce ads);
}
