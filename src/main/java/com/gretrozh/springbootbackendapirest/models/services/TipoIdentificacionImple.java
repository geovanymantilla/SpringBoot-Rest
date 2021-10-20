package com.gretrozh.springbootbackendapirest.models.services;

import com.gretrozh.springbootbackendapirest.models.dao.IIdentificacionDao;
import com.gretrozh.springbootbackendapirest.models.entity.Identificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoIdentificacionImple implements  ITipoIdentificacion{

    @Autowired
    IIdentificacionDao repository;

    @Override
    public List<Identificacion> findAll() {
        return (List<Identificacion>) repository.findAll();
    }

    @Override
    public Identificacion findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Identificacion save(Identificacion identificacion) {
        return repository.save(identificacion);
    }

    @Override
    public void delete(Integer id) {

        if(this.findById(id)==null)
            throw new RuntimeException("no s eencuentra el tipo de identificacion");

        this.repository.deleteById(id);

    }
}
