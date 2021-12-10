package com.filmer.dao;

import com.filmer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

    //-----METODOS---//
    //Creo un método que me devuelva un usuario en función de su userName
    //El metodo será Optional porque puede que encuentre el usuario o no
    Optional<Usuario> findByUsername(String username);

    //Genero un método boolean para saber si existe el usuario por el UserName
    boolean existsByUsername(String username);
}
