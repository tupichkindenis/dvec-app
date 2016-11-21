package com.whitesoft.announce.api.dto;

import org.springframework.stereotype.Component;

/**
 * Created by tupichkindenis on 01.11.16.
 */
@Component
public class AddAnnounceParam {

    private String author;
    private String header;
    private String text;

    public AddAnnounceParam() {
    }

    public AddAnnounceParam(String author, String header, String text) {
        this.author = author;
        this.header = header;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
