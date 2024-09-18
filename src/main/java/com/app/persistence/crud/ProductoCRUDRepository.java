package com.app.persistence.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

import com.app.persistence.entity.Producto;
public interface ProductoCRUDRepository extends CrudRepository<Producto, Long>{
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ? ORDER BY name ASC", nativeQuery = true)
    List<Producto> buscarPorCategoria(Long idCategoria);

    @Query(value = "SELECT * FROM productos WHERE stock <= stock", nativeQuery = true)
    List<Producto> listarPorStockBajo(int stock);
}
