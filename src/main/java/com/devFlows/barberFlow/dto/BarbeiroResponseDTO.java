package com.DevFlows.barberFlow.dto;

public record BarbeiroResponseDTO(

        Long id,
        String especialidade,
        String nome,
        String telefone,
        String cpf,
        Boolean ativo
){}
