package com.example.alkemy.api.service.inter;

import com.example.alkemy.api.model.entity.UsuarioEntity;
import com.example.alkemy.security.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUsuarioService extends UserDetailsService {

    ResponseEntity<String> crearUsuario(UsuarioEntity usuario);
}
