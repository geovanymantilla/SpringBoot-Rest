package com.gretrozh.springbootbackendapirest.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipo_identificacion" )
public class Identificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nombre;
}
