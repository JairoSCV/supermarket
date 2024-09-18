package com.app.domain.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private Integer dni;
    private String name;
    private String lastName;
    private Integer phone;
    private String email;
    private List<CompraDTO> comprasDtos;
}
