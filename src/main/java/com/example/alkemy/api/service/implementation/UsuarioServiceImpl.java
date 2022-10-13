package com.example.alkemy.api.service.implementation;

import com.example.alkemy.api.model.entity.UsuarioEntity;
import com.example.alkemy.api.repository.IUsuarioRepository;
import com.example.alkemy.api.service.inter.IUsuarioService;
import com.example.alkemy.security.AuthenticationRequest;
import com.example.alkemy.security.AuthenticationResponse;
import com.example.alkemy.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private EmailServiceImpl emailService;


    @Override
    public ResponseEntity <String> crearUsuario(UsuarioEntity usuario) {

        if(usuario.getUsername()==null || usuario.getEmail()==null || usuario.getPassword()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno o más campos vacios");

        UsuarioEntity u = loadUserByUsername(usuario.getEmail());

        if(u!=null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Mail ya registrado");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.enviarEmail(usuario));

    }

    @Override
    public UsuarioEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }
}
