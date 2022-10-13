package com.example.alkemy.api.repository;

import com.example.alkemy.api.model.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepository extends JpaRepository <GeneroEntity,Long> {
}
