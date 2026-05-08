package com.DevFlows.BarberFlow.Repositorys;

import com.DevFlows.BarberFlow.Entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = """
            SELECT * FROM clientes 
            WHERE LOWER(nome) LIKE LOWER(CONCAT('%', :nome, '%'))
            """, nativeQuery = true)
    List<Cliente> buscarClientesPorNome(String nome);


    boolean existsBytelefone(@NotBlank(message = "Telefone é obrigatório.") String telefone);
}
