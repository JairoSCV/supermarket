package com.app.domain.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {
    private Long id;
    private ClienteDTO clienteDto;
    private LocalDate fecha;
    private String medioPago;
    private String comentario;
    private Boolean state;
    private List<CompraProductoDTO> compraProductoDTOs;
}
