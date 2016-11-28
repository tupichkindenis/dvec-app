package com.whitesoft.announce.service;

import com.whitesoft.announce.model.Announce;
import com.whitesoft.announce.model.AnnounceStatus;
import com.whitesoft.announce.repository.AnnounceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.OverridesAttribute;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tupichkindenis on 08.11.16.
 */
@Service("AnnounceServiceImpl")
public class AnnounceServiceImpl implements AnnounceService {

    @Autowired
    AnnounceRepository announceRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Announce> getById(UUID id){
        return Optional.ofNullable(this.announceRepository.findOne(id));
    }

    @Override
    public List<Announce> getAll(){
        return announceRepository.findAllByOrderByCreateTime();
    }

    @Override
    @Transactional
    public Announce createAnnounce(@NotNull String author, @NotNull String header, @NotNull  String text) {

        Assert.hasText(author);
        Assert.hasText(header);
        Assert.hasText(text);

        Announce announce = new Announce();
        announce.setAuthor(author);
        announce.setHeader(header);
        announce.setText(text);

        return announceRepository.save(announce);
    }


    @Override
    public void deleteAnnounce(@NotNull Announce announce) {
        announceRepository.delete(announce);
    }

    @Override
    public Announce updateAnnounce(@NotNull Announce announce) {
        return announceRepository.save(announce);
    }

//    @Override
//    public List<Announce> searchAnnounce(Date beforeDate, Date afterDate, int count) {
//        List<Announce> announces =
//                 announceRepository.findByStatus(AnnounceStatus.PUBLISHED)
//                .stream()
//                         // берем только те, у которых дата публикации
//                         .sorted((p1, p2) -> -p1.getCreateTime().compareTo(p2.getCreateTime()))
//                         .filter(p -> (p.getCreateTime().before(beforeDate) && p.getCreateTime().after(afterDate)))
//                         .limit(count)
//                         .collect(Collectors.toList());
//        return announces;
//    }

    @Override
    public void showAnnounce(Announce announce) {

    }

    @Override
    public void hideAnnounce(Announce announce) {

    }
}
