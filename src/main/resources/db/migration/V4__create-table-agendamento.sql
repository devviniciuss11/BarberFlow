CREATE TABLE agendamento (
                             id BIGSERIAL PRIMARY KEY,

                             cliente_id BIGINT NOT NULL,

                             data DATE NOT NULL,

                             horario TIME NOT NULL,

                             servico VARCHAR(255) NOT NULL,

                             status VARCHAR(20) NOT NULL,

                             CONSTRAINT fk_agendamento_cliente
                                 FOREIGN KEY (cliente_id)
                                     REFERENCES cliente(id)
                                     ON DELETE CASCADE,

                             CONSTRAINT chk_status_agendamento
                                 CHECK (status IN (
                                                   'PENDENTE',
                                                   'CONFIRMADO',
                                                   'CANCELADO',
                                                   'CONCLUIDO'
                                     ))
);