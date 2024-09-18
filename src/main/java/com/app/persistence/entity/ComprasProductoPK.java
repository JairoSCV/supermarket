 package com.app.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable //porque esta clase luego se va a embeder dentro de otra clase
public class ComprasProductoPK{
    @Column(name = "id_compra")
    private Long idCompra;
    @Column(name = "id_producto")
    private Long idProducto;
}
