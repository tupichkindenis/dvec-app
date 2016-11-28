package com.whitesoft.controllers;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationForm {

    @NotNull
    @Size(min=1, max=128)
    private String author;

    @NotNull
    @Size(min=5, max=255)
    private String head;

    @NotNull
    @Size(min=1, max=2000)
    private String text;
}
