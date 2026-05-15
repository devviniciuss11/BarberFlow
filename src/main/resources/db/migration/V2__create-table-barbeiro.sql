CREATE TABLE barbeiro (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         telefone VARCHAR(13) NOT NULL UNIQUE,
                         especialidade VARCHAR(255) NOT NULL,
                         ativo BOOLEAN
);