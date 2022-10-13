package com.example.alkemy.api.controller;

import com.example.alkemy.api.model.dto.IPelicula;
import com.example.alkemy.api.model.entity.PeliculaEntity;
import com.example.alkemy.api.service.implementation.PeliculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    private PeliculaServiceImpl peliculaService;

    @GetMapping
    public List<IPelicula> listAll(){
        return peliculaService.listAll();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity <PeliculaEntity> detailsById(@PathVariable("id") Long id){
        return peliculaService.findById(id);
    }

    @PostMapping
    public ResponseEntity <?> save(@RequestBody PeliculaEntity pelicula){
        return peliculaService.save(pelicula);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PeliculaEntity pelicula){
        return peliculaService.update(pelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> delete(@PathVariable("id") Long id){
        return peliculaService.delete(id);
    }

    @GetMapping(params = "genre")
    public List<PeliculaEntity> movieByParam(@RequestParam(name = "name")String nombre, @RequestParam(name = "genre") Long idGenero, @RequestParam(name = "order", required = false)String order){
        return peliculaService.findByNameAndGenre(nombre,idGenero,order);
    }



}
