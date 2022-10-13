package com.example.alkemy.api.service.implementation;

import com.example.alkemy.api.model.dto.IPelicula;
import com.example.alkemy.api.model.entity.PeliculaEntity;
import com.example.alkemy.api.model.entity.PersonajeEntity;
import com.example.alkemy.api.repository.IPeliculaRepository;
import com.example.alkemy.api.service.inter.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

    @Autowired
    private IPeliculaRepository peliculaRepository;

    @Override
    public List<IPelicula> listAll() {
        return peliculaRepository.listAll();
    }

    @Override
    public ResponseEntity <PeliculaEntity> findById(Long id) {
        PeliculaEntity pelicula =peliculaRepository.findById(id).orElse(null);

        if(pelicula==null)
            return new ResponseEntity<PeliculaEntity>(pelicula,HttpStatus.NOT_FOUND);

        for(PersonajeEntity pj: pelicula.getPersonajes())
            pj.setPeliculas(null);

        return new ResponseEntity<PeliculaEntity>(pelicula,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(PeliculaEntity pelicula) {

        if(validate(pelicula)==false)
            return new ResponseEntity<String>("Error uno o mas campos vacios",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<PeliculaEntity>(peliculaRepository.save(pelicula),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity <?> update(PeliculaEntity pelicula) {

        if(validate(pelicula)==false)
            return new ResponseEntity<String>("Error uno o mas campos vacios",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<PeliculaEntity>(peliculaRepository.save(pelicula),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            peliculaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Personaje eliminado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha podido eliminar el personaje");
        }
    }

    @Override
    public List<PeliculaEntity> findByNameAndGenre(String name, Long idGenre, String order) {
        List<PeliculaEntity> peliculaList= peliculaRepository.findByTituloContainingAndGeneros_Id(name,idGenre);

        peliculaList = setCharactersNull(peliculaList);

        if(order=="ASC")
            peliculaList.sort(Comparator.comparing(PeliculaEntity::getFechaDeCreacion));
        else if (order=="DESC")
            peliculaList.sort(Comparator.comparing(PeliculaEntity::getFechaDeCreacion).reversed());

        return peliculaList;
    }

    public List <PeliculaEntity> setCharactersNull(List <PeliculaEntity> listaPeliculas){

        for(PeliculaEntity peli: listaPeliculas){
            for(PersonajeEntity pj: peli.getPersonajes())
                pj.setPeliculas(null);
        }
        return listaPeliculas;
    }

    public Boolean validate(PeliculaEntity pelicula){

        String imagen = pelicula.getImagen();
        String titulo = pelicula.getTitulo();
        Date fechaDeCreacion = pelicula.getFechaDeCreacion();
        Double calificacion = pelicula.getCalificacion();


        if(imagen ==null || titulo==null || fechaDeCreacion==null || calificacion==null || imagen == "" || titulo == "")
            return false;

        return  true;
    }

}
