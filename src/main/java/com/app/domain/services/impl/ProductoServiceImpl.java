package com.app.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.services.IProductoService;
import com.app.persistence.ProductoRepository;
import com.app.persistence.entity.Producto;
@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAll() {
        return productoRepository.getAll();
    }

    @Override
    public List<Producto> findByIdCategory(Long idCategoria) {
        return productoRepository.findByIdCategory(idCategoria);
    }

    @Override
    public List<Producto> findByStockLow(int stock) {
        return productoRepository.findByStockLow(stock);
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public boolean delete(Long id) {
        return productoRepository.delete(id);
    }
    
}
