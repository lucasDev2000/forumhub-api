CREATE TABLE topicos (
     id BIGINT NOT NULL AUTO_INCREMENT,
     titulo VARCHAR(255) NOT NULL,
     mensagem TEXT NOT NULL,
     data_criacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     estado VARCHAR(50) NOT NULL DEFAULT 'ABERTO',
     autor VARCHAR(255) NOT NULL,
     curso VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
);

CREATE INDEX idx_topicos_data_criacao
    ON topicos (data_criacao);