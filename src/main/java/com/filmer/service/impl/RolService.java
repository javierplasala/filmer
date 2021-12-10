package com.filmer.service.impl;

import com.filmer.dao.IRolDao;
import com.filmer.entities.Rol;
import com.filmer.enums.RolNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private IRolDao rolDao;

    //----METODOS---//

    public void guardarRol(Rol rol){
        rolDao.save(rol);
    }
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolDao.findByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre rolNombre){
        return rolDao.existsByRolNombre(rolNombre);
    }

}
