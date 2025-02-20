package com.tcc.api.config.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class CreatedUserDTO {
    private Long id;
    private String name;
    private String email;
}
