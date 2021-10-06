package com.gretrozh.springbootbackendapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.gretrozh.springbootbackendapirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
