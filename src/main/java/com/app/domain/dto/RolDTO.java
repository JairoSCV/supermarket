package com.app.domain.dto;

import com.app.security.entity.RolEnum;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolDTO {
    private Long id;
    private RolEnum nombre;
}
