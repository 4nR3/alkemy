package com.example.alkemy.api.repository;

import com.example.alkemy.api.model.dto.IPersonaje;
import com.example.alkemy.api.model.entity.PersonajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonajeRepository extends JpaRepository <PersonajeEntity, Long> {

    @Query(value = "SELECT p.imagen,p.nombre FROM Personaje p", nativeQuery = true)
    List<IPersonaje> listAll();

    List <PersonajeEntity> findByNombreContainingAndEdad(String nombre,Integer edad);

    List <PersonajeEntity> findByNombreContainingAndPeso(String nombre, Double weight);

    List<PersonajeEntity> findByNombreContainingAndPeliculas_Id(String nombre, Long id);



}
