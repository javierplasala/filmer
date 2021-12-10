package com.filmer.entities;

import com.filmer.enums.RolNombre;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull //No permite usuarios sin rol
    @Enumerated(EnumType.STRING) //Me graba en la DB el rol como un String
    @Column(unique = true)//el rol es único. Un usuario no puede tener roles repetidos
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(int id, @NonNull RolNombre rolNombre) {
        this.id = id;
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(@NonNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

}
