package com.example.alkemy.api.service.implementation;

import com.example.alkemy.api.model.dto.IPersonaje;
import com.example.alkemy.api.model.entity.PeliculaEntity;
import com.example.alkemy.api.model.entity.PersonajeEntity;
import com.example.alkemy.api.repository.IPersonajeRepository;
import com.example.alkemy.api.service.inter.IPersonajeService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

    private IPersonajeRepository personajeRepository;
    private PeliculaServiceImpl peliculaService;

    @Autowired
    public PersonajeServiceImpl(IPersonajeRepository personajeRepository, PeliculaServiceImpl peliculaService){
        this.personajeRepository=personajeRepository;
        this.peliculaService=peliculaService;
    }

    @Override
    public List<IPersonaje> listAll() {
        return personajeRepository.listAll();
    }

    @Override
    public ResponseEntity <?> save(PersonajeEntity personaje) {

        if(validate(personaje)==false)
            return new ResponseEntity<String>("Error, uno o mas campos vacios",HttpStatus.BAD_REQUEST);

       return new ResponseEntity<PersonajeEntity>(personajeRepository.save(personaje),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity <?> update(PersonajeEntity personaje){

        if(validate(personaje)==false)
            return new ResponseEntity<String>("Error, uno o mas campos vacios",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<PersonajeEntity>(personajeRepository.save(personaje),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try{
            personajeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Personaje eliminado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido eliminar el personaje");
        }
    }

    @Override
    public ResponseEntity <PersonajeEntity> findById(Long id){
        PersonajeEntity personaje =personajeRepository.findById(id).orElse(null);

        if(personaje == null)
            return new ResponseEntity<PersonajeEntity>(personaje,HttpStatus.NOT_FOUND);

        for (PeliculaEntity peli: personaje.getPeliculas())
            peli.setPersonajes(null);

        return new ResponseEntity<PersonajeEntity>(personaje,HttpStatus.OK);
    }


    @Override
    public List<PersonajeEntity> searchByAge(String name,Integer age) {
        List<PersonajeEntity> personajesList = personajeRepository.findByNombreContainingAndEdad(name,age);
        return setMoviesNull(personajesList);
    }

    @Override
    public List<PersonajeEntity> searchByMovieId(String name,Long id) {
        List<PersonajeEntity> personajesList =personajeRepository.findByNombreContainingAndPeliculas_Id(name,id);
        return setMoviesNull(personajesList);
    }

    @Override
    public List<PersonajeEntity> searchByWeight(String name,Double weight) {
        List<PersonajeEntity> personajesList =personajeRepository.findByNombreContainingAndPeso(name, weight);
        return setMoviesNull(personajesList);
    }

    public List<PersonajeEntity> setMoviesNull(List<PersonajeEntity> listaPersonajes){

        for(PersonajeEntity pjs:listaPersonajes){
            for (PeliculaEntity pelis: pjs.getPeliculas())
                pelis.setPersonajes(null);
        }

        return  listaPersonajes;
    }

    public Boolean validate(PersonajeEntity personaje){

        String imagen = personaje.getImagen();
        String nombre = personaje.getNombre();
        Integer edad = personaje.getEdad();
        Double peso = personaje.getPeso();
        String historia = personaje.getHistoria();

        if(imagen ==null || nombre==null || edad==null || peso==null || historia==null || imagen == "" || nombre == "" || historia == "")
            return false;

        return  true;
    }


}
