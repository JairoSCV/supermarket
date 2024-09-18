package com.app.domain.services;

import java.util.*;
import com.app.persistence.entity.Producto;

public interface IProductoService {
    List<Producto> getAll();

    List<Producto> findByIdCategory(Long idCategoria);

    List<Producto> findByStockLow(int stock);

    Optional<Producto> findById(Long id);

    Producto save(Producto producto);

    boolean delete(Long id);
}
