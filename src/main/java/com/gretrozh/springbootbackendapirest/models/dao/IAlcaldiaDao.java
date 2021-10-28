package com.gretrozh.springbootbackendapirest.models.dao;

import com.gretrozh.springbootbackendapirest.entities.Alcaldia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IAlcaldiaDao extends JpaRepository<Alcaldia,Integer> {
}
