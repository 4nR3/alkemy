package com.example.alkemy.api.service.inter;

import com.example.alkemy.api.model.dto.IPersonaje;
import com.example.alkemy.api.model.entity.PersonajeEntity;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonajeService {

    List<IPersonaje> listAll();
    ResponseEntity <?> save(PersonajeEntity personajeEntity);
    ResponseEntity <?> update(PersonajeEntity personajeEntity);
    ResponseEntity<String> delete(Long id);
    ResponseEntity <PersonajeEntity> findById(Long id);
    List <PersonajeEntity> searchByAge(String name,Integer age);
    List <PersonajeEntity> searchByMovieId(String name,Long id);
    List <PersonajeEntity> searchByWeight(String name,Double weight);

}
