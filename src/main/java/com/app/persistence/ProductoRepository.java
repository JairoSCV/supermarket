package com.app.persistence;

import com.app.persistence.crud.ProductoCRUDRepository;
import com.app.persistence.entity.Producto;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {
    @Autowired
    private ProductoCRUDRepository productoCRUDRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCRUDRepository.findAll();
    }

    public List<Producto> findByIdCategory(Long idCategoria){
        return productoCRUDRepository.buscarPorCategoria(idCategoria);
    }

    public List<Producto> findByStockLow(int stock){
        return productoCRUDRepository.listarPorStockBajo(stock);
    }

    public Optional<Producto> findById(Long id){
        return productoCRUDRepository.findById(id);
    }

    public Producto save(Producto producto){
        return productoCRUDRepository.save(producto);
    }

    public boolean delete(Long id){
        if(findById(id).isPresent()){
            productoCRUDRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
