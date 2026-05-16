package com.DevFlows.BarberFlow.service;

import com.DevFlows.BarberFlow.entity.Barbeiro;
import com.DevFlows.BarberFlow.dto.BarbeiroRequestDTO;
import com.DevFlows.BarberFlow.dto.BarbeiroResponseDTO;
import com.DevFlows.BarberFlow.repositorys.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Telefone Já Cadastrado.");
        }

        if(barbeiroRepository.existsByCpf(dto.cpf())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf Já Cadastrado.");
        }

        Barbeiro barbeiro = Barbeiro.builder()
                .nome(dto.nome())
                .especialidade(dto.especialidade())
                .telefone(dto.telefone())
                .senha(dto.senha())
                .cpf(dto.cpf())
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

    private BarbeiroResponseDTO toResponse(Barbeiro barbeiro) {
        return new BarbeiroResponseDTO(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getTelefone(),
                barbeiro.getEspecialidade(),
                barbeiro.getCpf(),
                barbeiro.getAtivo()
        );
    }
}