package com.filmer.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) //Para relaciones ManyToMany se usa el EAGER
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name="rol_id")) //Crea una tabla intermedia llamada "usuario_rol" con una clave foreane "usuario_id" para el usuario y "rol_id" para el rol
    private Set<Rol> roles = new HashSet<Rol>(); //Set asocia un par a una clave-valor. Va a asociar el rol con el usuario

    public Usuario() {
    }

    public Usuario(Long id, String username, String password, Set<Rol> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

}

