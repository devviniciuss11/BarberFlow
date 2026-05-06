package com.DevFlows.BarberFlow.Controlers;

import com.DevFlows.BarberFlow.Repositorys.ClienteRepository;
import com.DevFlows.BarberFlow.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControler {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity getAll(){
        List<Cliente> listaDeClientes = clienteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeClientes);
    }

}
