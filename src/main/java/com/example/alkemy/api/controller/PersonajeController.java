package com.example.alkemy.api.controller;

import com.example.alkemy.api.model.dto.IPersonaje;
import com.example.alkemy.api.model.entity.PersonajeEntity;
import com.example.alkemy.api.service.implementation.PersonajeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    private PersonajeServiceImpl personajeService;

    @Autowired
    public PersonajeController(PersonajeServiceImpl personajeService){
        this.personajeService=personajeService;
    }

    @GetMapping
    public List<IPersonaje> characterList(){
        return personajeService.listAll();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity <PersonajeEntity> details(@PathVariable("id") Long id){
        return personajeService.findById(id);
    }


    @GetMapping(params = "age")
    public List<PersonajeEntity> charactersByParam(@RequestParam(name = "name")String name, @RequestParam (name = "age")Integer age){
        return personajeService.searchByAge(name,age);
    }

    @GetMapping(params = "weight")
    public List<PersonajeEntity> charactersByParam(@RequestParam(name = "name")String name, @RequestParam (name = "weight")Double weight){
        return personajeService.searchByWeight(name, weight);
    }

    @GetMapping(params = "movies")
    public List<PersonajeEntity> charactersByParam(@RequestParam(name = "name")String name, @RequestParam (name = "movies")Long id){
        return personajeService.searchByMovieId(name, id);
    }

    @PostMapping
    public ResponseEntity<?> createCharacter(@RequestBody PersonajeEntity personaje){
        return personajeService.save(personaje);
    }


    @PutMapping
    public ResponseEntity <?> updateCharacter(@RequestBody PersonajeEntity personaje){
        return personajeService.update(personaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable ("id") Long id){
        return personajeService.delete(id);
    }

}
