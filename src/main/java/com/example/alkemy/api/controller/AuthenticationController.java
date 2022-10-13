package com.example.alkemy.api.controller;


import com.example.alkemy.api.model.entity.UsuarioEntity;
import com.example.alkemy.api.service.implementation.EmailServiceImpl;
import com.example.alkemy.api.service.implementation.UsuarioServiceImpl;
import com.example.alkemy.security.AuthenticationRequest;
import com.example.alkemy.security.AuthenticationResponse;
import com.example.alkemy.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioEntity usuario){
        return usuarioService.crearUsuario(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){

        String username = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        if(username==null || password==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El mail o la contraseña estan incompletos");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        final UserDetails userDetails = usuarioService.loadUserByUsername(authenticationRequest.getEmail());

        if(userDetails==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el usuario. Verifique que el mail o la contraseña sean correctos");

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));
    }


}
