package com.devFlows.barberFlow.entity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Barbeiro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String especialidade;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String nome;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;

//      Abaixo é o Relacionamento Barbeiro -> Agendamento
//    @OneToMany(mappedBy = "barbeiro")
//    private List<Agendamento> agendamento;

}