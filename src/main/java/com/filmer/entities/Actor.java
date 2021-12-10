package com.filmer.entities;

import javax.persistence.*;

@Entity
@Table(name = "actores")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pelicula pelicula;

    public Actor() {
    }

    public Actor(Long id, String nombre, String apellido,Pelicula pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pelicula= pelicula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
