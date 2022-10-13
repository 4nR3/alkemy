package com.example.alkemy.api.service.inter;

import com.example.alkemy.api.model.entity.GeneroEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IGeneroService {

    List<GeneroEntity> findAll();
    GeneroEntity save(GeneroEntity genero);
    GeneroEntity update(GeneroEntity genero);
    void delete(Long id);

}
