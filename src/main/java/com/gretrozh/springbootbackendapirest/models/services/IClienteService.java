package com.gretrozh.springbootbackendapirest.models.services;

import java.util.List;

import com.gretrozh.springbootbackendapirest.models.entity.Cliente;
 
public interface IClienteService {
	
	List<Cliente> findAll();
	Cliente findById(Long id);
	Cliente save(Cliente cliente);
	void delete(Long id);
	

}
