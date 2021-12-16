package com.filmer.dao;

import com.filmer.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentariosDao extends JpaRepository <Comentario, Long> {
}
