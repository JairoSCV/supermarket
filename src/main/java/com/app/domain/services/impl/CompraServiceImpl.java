package com.app.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.services.ICompraService;
import com.app.persistence.CompraRepository;
import com.app.persistence.entity.Compra;
@Service
public class CompraServiceImpl implements ICompraService{

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public List<Compra> getAll() {
        return compraRepository.getAll();
    }

    @Override
    public List<Compra> getByClient(Integer dni) {
        return compraRepository.getByClient(dni);
    }

    @Override
    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }
    
}
