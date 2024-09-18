package com.app.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.security.entity.Usuario;
@Repository
public interface UsuarioRepository extends CrudRepository<Long, Usuario>{

}
