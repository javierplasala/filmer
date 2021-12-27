package com.filmer.service.impl;

import com.filmer.dao.IActorDao;
import com.filmer.dao.IPeliculasDao;
import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;
import com.filmer.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    private IActorDao iActorDao;

    @Override
    public void save(Actor actor) {

        iActorDao.save(actor);
    }

    @Override
    public Actor obtenerActor(Long id) {
        return iActorDao.findById(id).orElse(null);
    }

    @Override
    public void eliminarActor(Long id) {
        iActorDao.deleteById(id);
    }
}
