package com.filmer.service;

import com.filmer.entities.Pelicula;

import java.util.List;

public interface IPeliculasService {
    //Metodo para guardar peliculas
    void save(Pelicula pelicula);
    //Método para listar películas
    List<Pelicula> listadoPeliculas();
    //Metodo para devolver peliculas por id
    Pelicula peliculaPorId(Long id);
}
