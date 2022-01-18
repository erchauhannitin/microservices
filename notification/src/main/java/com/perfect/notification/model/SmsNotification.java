package com.perfect.notification.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SmsNotification {

    @Id
    @SequenceGenerator(name = "notification_id_sequence",
            sequenceName = "notification_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence")
    private Integer id;
    private Integer customerId;
    private String emailId;

}
