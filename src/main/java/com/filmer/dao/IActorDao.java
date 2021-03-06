package com.filmer.dao;

import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface IActorDao extends JpaRepository<Actor, Long> {


}
