package com.example.alkemy.api.controller;

import com.example.alkemy.api.model.entity.GeneroEntity;
import com.example.alkemy.api.repository.IGeneroRepository;
import com.example.alkemy.api.service.implementation.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoService;

    @GetMapping
    public List<GeneroEntity> findAll(){
        return generoService.findAll();
    }

    @PostMapping
    public GeneroEntity save(@RequestBody GeneroEntity genero){
        return generoService.save(genero);
    }

    @PutMapping
    public GeneroEntity update(@RequestBody GeneroEntity genero){
        return generoService.update(genero);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        generoService.delete(id);
    }

}
