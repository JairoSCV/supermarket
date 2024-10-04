package com.app.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.security.entity.Rol;
import com.app.security.entity.RolEnum;

@Repository
public interface RolRepository extends CrudRepository<Rol,Long>{
    public Rol findRolByNombre(RolEnum nombre);

    List<Rol> findRolByNombreIn(List<String> roleNames);

    @Query(value="SELECT * FROM Rol WHERE rol_name = ?", nativeQuery = true)
    public Rol findRolbyNombreString(String rol);
}
