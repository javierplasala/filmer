package com.filmer.entities;

import javax.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue
    private Long id;
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY) //Muchos comentarios a un usuario
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY) //Muchos comentarios a una pelicula
    private Pelicula pelicula;

    public Comentario() {
    }

    public Comentario(Long id, String texto, Usuario usuario, Pelicula pelicula) {
        this.id = id;
        this.texto = texto;
        this.usuario = usuario;
        this.pelicula = pelicula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
