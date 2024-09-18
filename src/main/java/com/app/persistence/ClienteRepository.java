package com.app.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.persistence.crud.ClienteCRUDRepository;
import com.app.persistence.entity.Cliente;

@Repository
public class ClienteRepository {

    @Autowired
    private ClienteCRUDRepository clienteCRUDRepository;

    public Optional<Cliente> findClientByDni(Integer dni){
        return clienteCRUDRepository.findClientByDni(dni);
    }
}
