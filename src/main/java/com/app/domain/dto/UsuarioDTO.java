package com.app.domain.dto;

import java.util.*;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private Set<RolDTO> rolesDtos = new HashSet<>();
}
