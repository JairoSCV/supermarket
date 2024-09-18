package com.app.persistence.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.persistence.entity.Cliente;

public interface ClienteCRUDRepository extends CrudRepository<Cliente, Long>{
    @Query(value = "SELECT * FROM cliente WHERE dni = ?", nativeQuery = true)
    Optional<Cliente> findClientByDni(Integer dni);
}
