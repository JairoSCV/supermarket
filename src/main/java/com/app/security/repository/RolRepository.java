package com.app.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.security.entity.Rol;
import com.app.security.entity.RolEnum;

@Repository
public interface RolRepository extends CrudRepository<Rol,Long>{
    public Rol findRolByNombre(RolEnum nombre);
}
