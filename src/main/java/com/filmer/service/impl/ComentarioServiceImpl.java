package com.filmer.service.impl;

import com.filmer.dao.IComentariosDao;
import com.filmer.entities.Comentario;
import com.filmer.service.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements IComentarioService {

    @Autowired
    IComentariosDao comentariosDao;

    @Override
    public void saveComentario(Comentario comentario) {
    comentariosDao.save(comentario);
    }

    @Override
    public Comentario buscarPorId(Long id) {
        return comentariosDao.findById(id).orElse(null);
    }

    @Override
    public void eliminarComentario(Long id) {
        comentariosDao.deleteById(id);
    }
}
