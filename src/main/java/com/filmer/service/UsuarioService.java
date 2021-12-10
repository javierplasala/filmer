package com.filmer.service;

import com.filmer.dao.IUsuarioDao;
import com.filmer.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    //Metodo para devolver un listado de usuarios
    public List<Usuario> listadoUsuarios(){
        return usuarioDao.findAll();
    }

    //Metodo para devolver un usuario en función del ID
    public Optional<Usuario> getUsuarioById(Long id){
        return usuarioDao.findById(id);
    }
    //Metodo para devolver un usuario en función del UserName (metodo creado en el IUsuarioDao)
    public Optional<Usuario> getUsuarioByUserName(String username){
        return usuarioDao.findByUsername(username);
    }

    //Metodo para guardar un usuario
    public void guardarUsuario(Usuario usuario){
        usuarioDao.save(usuario);
    }

    //Metodo para chequear si existe un usuario por Id
    public boolean existsById(Long id){
        return usuarioDao.existsById(id);
    }

    //Metodo para chequear si existe un UserName (metodo creado en el IUsuarioDao)
    public boolean existsByUserName(String username){
        return usuarioDao.existsByUsername(username);
    }
}
