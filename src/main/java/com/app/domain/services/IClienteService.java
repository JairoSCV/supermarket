package com.app.domain.services;

import java.util.Optional;

import com.app.persistence.entity.Cliente;

public interface IClienteService {
    Optional<Cliente> findClientByDni(Integer dni);
}
