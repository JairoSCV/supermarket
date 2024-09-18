package com.app.webcontroller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.dto.ClienteDTO;
import com.app.domain.dto.CompraDTO;
import com.app.domain.services.IClienteService;
import com.app.domain.services.ICompraService;
import com.app.excepciones.ExcepcionPersonalizada;
import com.app.persistence.entity.Cliente;
import com.app.persistence.entity.Compra;
import com.app.persistence.entity.ComprasProducto;
import com.app.persistence.entity.ComprasProductoPK;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ICompraService compraService;

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<Compra> save(@RequestBody CompraDTO compraDTO){

        //Creamos la compra sin setear los datos de la Lista CompraProductos
        Compra compra = Compra.builder()
                        .id(compraDTO.getId())
                        .cliente(Cliente.builder()
                                .id(compraDTO.getClienteDto().getId())
                                .build())
                        .fecha(compraDTO.getFecha())
                        .medioPago(compraDTO.getMedioPago())
                        .comentario(compraDTO.getComentario())
                        .state(compraDTO.getState())
                        .build();

        //creamos la lista de CompraProductos 
        List<ComprasProducto> comprasProductos = compraDTO.getCompraProductoDTOs().stream() 
        .map(cp -> ComprasProducto.builder()
            .cantidad(cp.getCantidad())
            .estado(cp.getEstado())
            .compra(compra)
            .id(ComprasProductoPK.builder().idProducto(cp.getProductoDTO().getId()).build())
            .total(cp.getTotal()).build()).toList();

        //Seteamos la lista de CompraProducto a la instancia de Compra para poder completarlo
        compra.setComprasProductos(comprasProductos);


        compraService.save(compra);
        
        return new ResponseEntity<Compra>(compra, HttpStatus.CREATED);
    }

    @GetMapping("/cliente/{dni}")
    public ResponseEntity<List<CompraDTO>> getByCliente(@PathVariable Integer dni){

        if (!clienteService.findClientByDni(dni).isPresent()) {
            throw new ExcepcionPersonalizada("El cliente no existe");
        }

        List<CompraDTO> compraDTOs = compraService.getByClient(dni).stream()
                                    .map(compra -> CompraDTO.builder()
                                        .id(compra.getId())
                                        .fecha(compra.getFecha())
                                        .medioPago(compra.getMedioPago())
                                        .comentario(compra.getComentario())
                                        .state(compra.getState())
                                        .clienteDto(ClienteDTO.builder()
                                                    .dni(compra.getCliente().getDni())
                                                    .name(compra.getCliente().getName())
                                                    .lastName(compra.getCliente().getLastName())
                                                    .build())
                                        .build()).toList();

        return new ResponseEntity<>(compraDTOs, HttpStatus.OK);
    }

}
