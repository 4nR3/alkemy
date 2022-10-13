package com.example.alkemy.api.repository;

import com.example.alkemy.api.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    UsuarioEntity findByEmail(String email);
}
