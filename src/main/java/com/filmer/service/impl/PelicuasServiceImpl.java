package com.filmer.service.impl;

import com.filmer.dao.IPeliculasDao;
import com.filmer.entities.Pelicula;
import com.filmer.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PelicuasServiceImpl implements IPeliculasService {

    @Autowired
    private IPeliculasDao iPeliculasDao;

    @Override
    public void save(Pelicula pelicula) {
        iPeliculasDao.save(pelicula);
    }
}
