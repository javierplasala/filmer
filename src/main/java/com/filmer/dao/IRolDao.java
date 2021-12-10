package com.filmer.dao;

import com.filmer.entities.Rol;
import com.filmer.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolDao extends JpaRepository<Rol, Integer> {
    //------METODOS-----//
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existsByRolNombre(RolNombre rolNombre);
}
