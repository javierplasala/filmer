package com.filmer.dao;

import com.filmer.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPeliculasDao extends JpaRepository<Pelicula, Long> {
    Pelicula findByTitulo(String titulo);
}
