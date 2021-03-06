package com.whitesoft.messaging.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class PushNotification {

    /**
     * Портянка  только для IoS
     */
    private class Notification {

        /**
         * Заголовок для уведомления
         */
        @JsonProperty("title")
        private final String title;

        /**
         * Тело уведомления
         */
        @JsonProperty("body")
        private final String body;

        /**
         * Звуковое сообщение
         */
        @JsonProperty("sound")
        private final String sound = "default";

        /**
         * Индикатор для отображения отметки о наличие непрочитанных уведомлений.
         */
        @JsonProperty("badge")
        private final int badge = 1;

        public Notification(String title, String body) {
            this.title = title;
            this.body = body;
        }
    }

    /**
     * Передаем данные вместе с push-уведомлением.
     */
    private class Data {

        /**
         * Внутри системный, уникальный идентификатор объявления
         */
        @JsonProperty("id")
        private final String id;

        /**
         * Дата объявления.
         */
        @JsonProperty("date")
        private final Date date;

        public Data(final UUID uuid, final Date date){
            this.id = uuid.toString();
            this.date = date;
        }

    }

    /**
     * Адресат
     */
    @JsonProperty("to")
    private String to;

    /**
     * Собственно уведомление.
     */
    @JsonProperty("notification")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Notification notification;


    /**
     * Данные.
     */
    @JsonProperty("data")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Data data;

    /**
     * Приоритет уведомление
     */
    @JsonProperty("priority")
    private final String priority = "high";

    /**
     *
     * @param to
     * @param uuid
     * @param title
     * @param body
     */
    public PushNotification(final String to, final UUID uuid, final Date date, final String title, final String body){
        this.to = to;
        this.data = new Data(uuid, date);
        this.notification = new Notification(title,body);
    }
}
