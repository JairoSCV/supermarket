package com.app.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.security.entity.Usuario;
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    public Optional<Usuario> findUsuarioByUsername(String username);
}