package com.perfect.microservices.payment.model;


import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class CaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private String active;
    private String roles;

}
