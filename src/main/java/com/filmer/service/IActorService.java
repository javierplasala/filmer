package com.filmer.service;

import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;

public interface IActorService {
    //Metodo para guardar actores
    void save(Actor actor);

    //Metodo para obtener un actor por su id
    Actor obtenerActor(Long id);

    //Metodo para eliminar actores
    void eliminarActor(Long id);

}
