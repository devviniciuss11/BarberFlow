CREATE TABLE cliente (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         telefone VARCHAR(50),
                         senha VARCHAR(255)
);