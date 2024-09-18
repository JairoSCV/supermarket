package com.app.domain.dto;


import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String name;
    private CategoriaDTO categoriaDto;
    private Double precioVenta;
    private Integer stock;
    private Boolean state;
    private String codigoBarras;
}
