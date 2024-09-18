package com.app.webcontroller.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.dto.ClienteDTO;
import com.app.domain.services.IClienteService;
import com.app.excepciones.ExcepcionPersonalizada;
import com.app.persistence.entity.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClienteDTO> findByDni(@PathVariable Integer dni){
        Optional<Cliente> optional = clienteService.findClientByDni(dni);
        if(optional.isEmpty()){
            throw new ExcepcionPersonalizada("El cliente no existe");
        }
        Cliente cliente = optional.get();
        ClienteDTO clienteDTO = ClienteDTO.builder()
                                .id(cliente.getId())
                                .name(cliente.getName())
                                .lastName(cliente.getLastName())
                                .phone(cliente.getPhone())
                                .email(cliente.getEmail())
                                .build();
        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
    }
}
