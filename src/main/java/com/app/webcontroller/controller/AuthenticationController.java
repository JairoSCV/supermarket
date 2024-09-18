package com.app.webcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import com.app.domain.dto.UsuarioDTO;
import com.app.excepciones.ExcepcionPersonalizada;
import com.app.security.entity.Rol;
import com.app.security.entity.Usuario;
import com.app.security.service.IUsuarioService;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/save")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDTO usuarioDto){
        if (usuarioDto.getUsername().isBlank() || usuarioDto.getPassword().isBlank() || usuarioDto.getRolesDtos().isEmpty()) {
            throw new ExcepcionPersonalizada("Algunos campos están vacíos");
        }

        Set<Rol> rolesUsuarioNuevo = usuarioDto.getRolesDtos().stream().map(rolDTO -> Rol.builder()
                                    .id(rolDTO.getId())
                                    .nombre(rolDTO.getNombre())
                                    .build()).collect(Collectors.toSet());

        Usuario usuario = Usuario.builder()
                            .username(usuarioDto.getUsername())
                            .password(usuarioDto.getPassword())
                            .roles(rolesUsuarioNuevo)
                            .build();

        usuarioService.crearUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
