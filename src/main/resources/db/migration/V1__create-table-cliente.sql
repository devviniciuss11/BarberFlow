CREATE TABLE Cliente (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         telefone VARCHAR(13) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL,
                         agendamentoPoints INTEGER
);