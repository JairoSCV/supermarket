package com.app.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compras_productos")
public class ComprasProducto implements Serializable{
    @EmbeddedId
    private ComprasProductoPK id; //ID compuesta
    private Integer cantidad;
    private BigDecimal total;
    private Boolean estado;
    
    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    @MapsId("idCompra")
    @JsonIgnore
    private Compra compra;
}
