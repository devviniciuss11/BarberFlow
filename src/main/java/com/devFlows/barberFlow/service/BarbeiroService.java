package com.devFlows.barberFlow.service;

import com.devFlows.barberFlow.entity.Barbeiro;
import com.devFlows.barberFlow.dto.BarbeiroRequestDTO;
import com.devFlows.barberFlow.dto.BarbeiroResponseDTO;
import com.devFlows.barberFlow.repositorys.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BarbeiroService{
    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroResponseDTO cadastrar(BarbeiroRequestDTO dto){

        if (barbeiroRepository.existsByTelefone(dto.telefone())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone Já Cadastrado.");
        }

        Barbeiro barbeiro = Barbeiro.builder()
                .nome(dto.nome())
                .especialidade(dto.especialidade())
                .telefone(dto.telefone())
                .ativo(true)
                .build();

        Barbeiro salvo = barbeiroRepository.save(barbeiro);
        return toResponse(salvo);
    }

    public BarbeiroResponseDTO buscarPorId(Long id){

        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro não Encontrado.  ID: " + id));
        return toResponse(barbeiro);
    }

    public List<BarbeiroResponseDTO> listarTodos(){
        return barbeiroRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BarbeiroResponseDTO atualizar(Long id, BarbeiroRequestDTO dto){

        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro Não Encontrado. ID: " + id));
                barbeiro.setNome(dto.nome());
                barbeiro.setEspecialidade(dto.especialidade());
                barbeiro.setTelefone(dto.telefone());
        return toResponse(barbeiroRepository.save(barbeiro));
    }

    public void deletar(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbeiro Não Encontrado. ID: " + id));

        barbeiro.setAtivo(false);
        barbeiroRepository.save(barbeiro);
    }

    private BarbeiroResponseDTO toResponse(Barbeiro barbeiro){
        return new BarbeiroResponseDTO(barbeiro.getId(), barbeiro.getNome(), barbeiro.getTelefone(), barbeiro.getEspecialidade(), barbeiro.getAtivo());
    }
}
