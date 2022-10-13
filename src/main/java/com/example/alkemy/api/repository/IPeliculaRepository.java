package com.example.alkemy.api.repository;

import com.example.alkemy.api.model.dto.IPelicula;
import com.example.alkemy.api.model.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeliculaRepository extends JpaRepository <PeliculaEntity, Long> {

    @Query(value = "SELECT p.imagen,p.titulo,p.fecha_De_Creacion FROM Pelicula p", nativeQuery = true)
    List<IPelicula> listAll();

    List<PeliculaEntity> findByTituloContainingAndGeneros_Id(String titulo, Long id);











}
