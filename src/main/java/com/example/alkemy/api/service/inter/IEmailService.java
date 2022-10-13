package com.example.alkemy.api.service.inter;

import com.example.alkemy.api.model.entity.UsuarioEntity;

public interface IEmailService {

    String enviarEmail(UsuarioEntity usuario);
}
