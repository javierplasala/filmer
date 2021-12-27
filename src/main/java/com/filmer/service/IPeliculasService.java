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

    //Metodo para borrar peliculas por id
    void eliminarPelicula(Long id);

    //Metodo para buscar pelicula por Título
    Pelicula peliPorTitulo(String titulo);
}
