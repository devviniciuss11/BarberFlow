package com.DevFlows.BarberFlow.Repositorys;

import com.DevFlows.BarberFlow.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
