package com.example.alkemy.api.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pelicula")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String imagen;
    @NotNull
    private String titulo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date fechaDeCreacion;

    @NotNull
    private Double calificacion;

    @ManyToMany
    @JoinTable(name = "pelicula_genero",
            joinColumns = @JoinColumn(name = "pelicula"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private List<GeneroEntity> generos = new ArrayList<GeneroEntity>();

    @ManyToMany(mappedBy = "peliculas")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<PersonajeEntity> personajes = new ArrayList<>();

    public PeliculaEntity() {
    }

    public PeliculaEntity(Long id, String imagen, String titulo, Date fechaDeCreacion, Double calificacion, List<PersonajeEntity> personajes, List<GeneroEntity> generos) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.personajes = personajes;
        this.generos = generos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public List<GeneroEntity> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroEntity> generos) {
        this.generos = generos;
    }

    public List<PersonajeEntity> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeEntity> personajes) {
        this.personajes = personajes;
    }

    @Override
    public String toString() {
        return "PeliculaEntity{" +
                "id=" + id +
                ", imagen='" + imagen + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", calificacion=" + calificacion +
                ", personajes=" + personajes +
                ", generos=" + generos +
                '}';
    }
}
