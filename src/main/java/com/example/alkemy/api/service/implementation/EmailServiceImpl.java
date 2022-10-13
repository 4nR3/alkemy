package com.example.alkemy.api.service.implementation;

import com.example.alkemy.api.model.entity.UsuarioEntity;
import com.example.alkemy.api.service.inter.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emisor;


    @Override
    public String enviarEmail(UsuarioEntity usuario) {
        try {
            String  receptor = usuario.getEmail();
            String asunto ="Bienvenido a Alkemy Movie Service";
            String texto= "Hola "+usuario.getUsername()+", este mail confirma que se ha registrado exitosamente en el servicio de streaming de peliculas de Alkemy.";

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emisor);
            simpleMailMessage.setTo(receptor);
            simpleMailMessage.setSubject(asunto);
            simpleMailMessage.setText(texto);

            javaMailSender.send(simpleMailMessage);

            return "Se ha enviado un correo a su casilla de correos";
        }
        catch (Exception e){
            return "Ha ocurrido un error "+e;
        }

    }
}
