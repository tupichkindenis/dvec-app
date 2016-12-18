package com.whitesoft.announce.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by tupichkindenis on 08.11.16.
 */
@ApiModel
public class AnnounceDTO {

    private String id;
    private String createTime;
    private String author;
    private String header;
    private String text;

    public AnnounceDTO() {
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for property 'createTime'.
     *
     * @return Value for property 'createTime'.
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Setter for property 'createTime'.
     *
     * @param createTime Value to set for property 'createTime'.
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Getter for property 'author'.
     *
     * @return Value for property 'author'.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for property 'author'.
     *
     * @param author Value to set for property 'author'.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter for property 'header'.
     *
     * @return Value for property 'header'.
     */
    public String getHeader() {
        return header;
    }

    /**
     * Setter for property 'header'.
     *
     * @param header Value to set for property 'header'.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Getter for property 'text'.
     *
     * @return Value for property 'text'.
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for property 'text'.
     *
     * @param text Value to set for property 'text'.
     */
    public void setText(String text) {
        this.text = text;
    }
}
