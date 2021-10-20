package com.gretrozh.springbootbackendapirest.models.services;

import com.gretrozh.springbootbackendapirest.models.entity.Identificacion;
import org.springframework.data.repository.CrudRepository;

public interface IIdentificacionDao extends CrudRepository<Identificacion,Integer> {
    
}
