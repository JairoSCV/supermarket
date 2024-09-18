package com.app.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.security.entity.Usuario;

public interface IUsuarioService extends UserDetailsService{
    public Usuario crearUsuario(Usuario usuario);
}
