package com.example.alkemy.api.service.implementation;

import com.example.alkemy.api.model.entity.GeneroEntity;
import com.example.alkemy.api.repository.IGeneroRepository;
import com.example.alkemy.api.service.inter.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private IGeneroRepository generoRepository;

    @Override
    public List<GeneroEntity> findAll() {
        return generoRepository.findAll();
    }

    @Override
    public GeneroEntity save(GeneroEntity genero) {
        return generoRepository.save(genero);
    }

    @Override
    public GeneroEntity update(GeneroEntity genero) {
        return generoRepository.save(genero);
    }

    @Override
    public void delete(Long id) {
        generoRepository.deleteById(id);
    }
}
