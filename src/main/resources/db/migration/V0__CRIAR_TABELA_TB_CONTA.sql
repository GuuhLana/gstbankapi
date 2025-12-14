-- V0__CRIAR_TABELA_TB_CONTA.sql
-- Cria a tabela TB_CONTA (tb_conta) do zero, com constraints

CREATE TABLE IF NOT EXISTS tb_conta (
    id      BIGSERIAL PRIMARY KEY,
    titular VARCHAR(120) NOT NULL,
    agencia INTEGER      NOT NULL,
    numero  INTEGER      NOT NULL,
    saldo   NUMERIC(15,2) NOT NULL DEFAULT 0,
    cpf     VARCHAR(11)  NOT NULL,
    idade   INTEGER      NOT NULL,
    tipo    VARCHAR(20)  NOT NULL,

    CONSTRAINT uk_tb_conta_agencia_numero UNIQUE (agencia, numero),
    CONSTRAINT uk_tb_conta_cpf UNIQUE (cpf),
    CONSTRAINT ck_tb_conta_tipo CHECK (tipo IN ('GOLD','PLATINUM','BLACK')),
    CONSTRAINT ck_tb_conta_idade CHECK (idade BETWEEN 0 AND 120)
);
