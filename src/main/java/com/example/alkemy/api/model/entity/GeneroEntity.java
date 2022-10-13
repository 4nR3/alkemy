package com.example.alkemy.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Genero")
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
    private String imagen;

    @ManyToMany(mappedBy = "generos")
    @JsonIgnore
    private List<PeliculaEntity> peliculas = new ArrayList<>();

    public GeneroEntity() {
    }

    public GeneroEntity(Long id, String nombre, String imagen, List<PeliculaEntity> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.peliculas = peliculas;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @JsonIgnore
    public List<PeliculaEntity> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<PeliculaEntity> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "GeneroEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", peliculas=" + peliculas +
                '}';
    }
}
