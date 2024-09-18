package com.app.domain.dto;

import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    private String description;
    private Boolean state;
}
