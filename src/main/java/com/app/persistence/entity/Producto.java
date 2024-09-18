package com.app.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "productos")
public class Producto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "precio_venta")
    private Double precioVenta;
    private Integer stock;
    private Boolean state;
}
