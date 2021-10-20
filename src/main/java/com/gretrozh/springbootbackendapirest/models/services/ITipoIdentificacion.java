package com.gretrozh.springbootbackendapirest.models.services;

import com.gretrozh.springbootbackendapirest.models.entity.Identificacion;

import java.util.List;

public interface ITipoIdentificacion {

    List<Identificacion> findAll();
    Identificacion findById(Long id);
    Identificacion save(Identificacion identificacion);
    void delete(Long id);

}
