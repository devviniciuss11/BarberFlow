package com.devFlows.barberFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(
        @NotBlank(message = "Nome do Cliente é obrigatório.")
        String nome,

        @NotBlank(message = "Telefone é obrigatório.")
        String telefone,

        @NotBlank(message = "Senha é obrigatória.")
        String senha
) {}
