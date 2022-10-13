package com.example.alkemy.api.service.inter;

import com.example.alkemy.api.model.dto.IPelicula;
import com.example.alkemy.api.model.entity.PeliculaEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IPeliculaService {

    List<IPelicula> listAll();
    ResponseEntity <PeliculaEntity> findById(Long id);
    ResponseEntity <?> save(PeliculaEntity pelicula);
    ResponseEntity<?> update(PeliculaEntity pelicula);
    ResponseEntity<String> delete(Long id);
    List <PeliculaEntity> findByNameAndGenre(String name, Long idGenre, String order);


}
