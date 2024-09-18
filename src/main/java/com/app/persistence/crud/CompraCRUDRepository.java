package com.app.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.persistence.entity.Compra;

public interface CompraCRUDRepository extends CrudRepository<Compra,Long>{
    @Query(value = "SELECT c.id, c.fecha, c.medio_pago, c.comentario, c.state, c.id_cliente FROM compras c inner join cliente cl on c.id_cliente = cl.id where cl.dni=?", nativeQuery = true)
    List<Compra> getByClient(Integer dni);
}
