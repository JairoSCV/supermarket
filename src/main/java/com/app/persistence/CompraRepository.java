package com.app.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.persistence.crud.CompraCRUDRepository;
import com.app.persistence.entity.Compra;

@Repository
public class CompraRepository {
    @Autowired
    private CompraCRUDRepository compraCRUDRepository;

    public List<Compra> getAll(){
        return (List<Compra>) compraCRUDRepository.findAll();
    };

    public List<Compra> getByClient(Integer dni){
        return compraCRUDRepository.getByClient(dni);
    };

    public Compra save(Compra compra){
        //cada compraProducto se le setea la compra
        compra.getComprasProductos().forEach(prod -> prod.setCompra(compra));
        return compraCRUDRepository.save(compra);
    };

}
