package com.app.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.security.controller.dto.AuthCreateUserRequest;
import com.app.security.controller.dto.AuthLoginRequest;
import com.app.security.controller.dto.AuthResponse;
import com.app.security.entity.Usuario;

public interface IUsuarioService extends UserDetailsService{
    public Usuario crearUsuario(Usuario usuario);
    public AuthResponse loginUser(AuthLoginRequest authLoginRequest);
    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest);
}
