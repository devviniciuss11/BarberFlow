package com.DevFlows.BarberFlow.Service;

import com.DevFlows.BarberFlow.Entity.Cliente;
import com.DevFlows.BarberFlow.Repositorys.ClienteRepository;
import com.DevFlows.BarberFlow.dto.ClienteRequestDTO;
import com.DevFlows.BarberFlow.dto.ClienteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto){
        Cliente cliente = Cliente.builder().nome(dto.nome()).telefone(dto.telefone()).senha(dto.senha()).build();
        if(clienteRepository.existsBytelefone(dto.telefone())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Telefone Já Cadastrado");
        }
        return toResponse(clienteRepository.save(cliente));

    }
    public ClienteResponseDTO buscarPorId(Long id){
        return toResponse(buscarEntidade(id));
    }
    public void excluirClientePorId(Long id){
        if(!clienteRepository.existsById(id)){
            throw new RuntimeException("Cliente Não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    private Cliente buscarEntidade(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    private ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }
    public List<ClienteResponseDTO> listar(String busca){
        List<Cliente> clientes = (busca == null || busca.isBlank())
                ? clienteRepository.findAll()
                : clienteRepository.buscarClientesPorNome(busca.trim());
        return clientes.stream().map(this::toResponse).toList();
    }


    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = buscarEntidade(id);
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setSenha(dto.senha());
        return toResponse(clienteRepository.save(cliente));
    }

}
