package com.app.domain.dto;

import java.math.BigDecimal;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraProductoDTO {
    private Integer cantidad;
    private BigDecimal total;
    private Boolean estado;
    private CompraDTO compraDto;
    private ProductoDTO productoDTO;
}
