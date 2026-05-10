package com.devFlows.barberFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BarbeiroRequestDTO (

        Long id,

        @NotBlank(message = "O Nome é Obrigatório!")
        @Size(max = 100, message = "O Nome deve ter no Max: 100 Caracteres!")
        String nome,

        @NotBlank(message = "A especialidade é Obrigatória!")
        @Size(max = 100, message = "A especialidade deve ter no Max: 100 Caracteres!")
        String especialidade,

        @NotBlank(message = "O telefone é Obrigatório!")
        @Size(max = 20, message = "O telefone deve ter no Max: 15 Caracteres!")
        String telefone

        ){}



