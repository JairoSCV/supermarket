package com.app.domain.services;

import java.util.List;

import com.app.persistence.entity.Compra;

public interface ICompraService {
    public List<Compra> getAll();

    public List<Compra> getByClient(Integer dni);

    public Compra save(Compra compra);
}
