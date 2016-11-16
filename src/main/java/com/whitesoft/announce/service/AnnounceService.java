package com.whitesoft.announce.service;

import com.whitesoft.announce.model.Announce;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by tupichkindenis on 31.10.16.
 */
public interface AnnounceService {

    /**
     * Получить Объявление по его идентификатору.
     * @param id
     * @return
     */
    Optional<Announce> getById(UUID id);

    List<Announce> getAll();

    /**
     * Создать новое объявление.
     * @return
     */
    Announce createAnnounce(String author, String header, String text);

    /**
     * Удалить сущесвующие объявление.
     * @param announce
     * @return
     */
    void deleteAnnounce(Announce announce);

    /**
     * Обновить существующие объявление.
     * @param announce
     * @return
     */
    Announce updateAnnounce(Announce announce);

    /**
     *
     * @param since
     * @param until
     * @param count
     * @return
     */
    List<Announce> searchAnnounce(Date since, Date until, int count);

    /**
     *
     * @param announce
     */
    void showAnnounce(Announce announce);

    /**
     *
     * @param announce
     */
    void hideAnnounce(Announce announce);
}
