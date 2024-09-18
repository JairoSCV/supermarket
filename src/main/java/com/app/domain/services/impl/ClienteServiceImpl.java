package com.app.domain.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.services.IClienteService;
import com.app.persistence.ClienteRepository;
import com.app.persistence.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> findClientByDni(Integer dni) {
        return clienteRepository.findClientByDni(dni);
    }

}
