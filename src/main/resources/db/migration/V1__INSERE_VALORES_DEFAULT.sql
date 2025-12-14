-- V1__INSERE_CLIENTES_TESTE.sql
-- Seed único com 220 contas (>= 200), nomes reais, cpf único, idade, tipo, saldo variado

WITH nomes AS (
    SELECT
        row_number() OVER () AS rn,
        p1.nome || ' ' || p2.nome || ' ' || s1.sobrenome AS titular
    FROM (VALUES
        ('Lucas'),('Ana'),('Marcos'),('Larissa'),('Felipe'),('Carla'),('Bruno'),('Renata'),('Ricardo'),('Gabriela'),
        ('Thiago'),('Mariana'),('José'),('Amanda'),('Fernando'),('Paula'),('Diego'),('Juliana'),('Rodrigo'),('Vanessa'),
        ('Vinícius'),('Beatriz'),('Camila'),('Daniel'),('Eduardo'),('Letícia'),('Mateus'),('Rafael'),('Aline'),('Cristina'),
        ('Patrícia'),('Sabrina'),('Tatiane'),('William'),('Guilherme'),('Henrique'),('Igor'),('João'),('Leonardo'),('Daniela'),
        ('Fernanda'),('Natália'),('Sérgio'),('Roberto'),('Alexandre'),('Maurício'),('André'),('César'),('Caio'),('Vitor'),
        ('Isabela'),('Luana'),('Michele'),('Priscila'),('Rafaela'),('Bianca'),('Bárbara'),('Helena'),('Clara'),('Luiza')
    ) AS p1(nome)
    CROSS JOIN (VALUES
        ('Paulo'),('Maria'),('Clara'),('Luiza'),('Rosa'),('Aparecida'),('Helena'),('Cristiane'),('Bárbara'),('Bianca'),
        ('Rafael'),('Henrique'),('Augusto'),('Antônio'),('Carlos'),('Júlio'),('Catarina'),('Gabriel'),('Miguel'),('Sophia'),
        ('Daniel'),('Bruna'),('Marina'),('Vitória'),('Renato'),('Pedro'),('Isadora'),('Larissa'),('Felipe'),('Camila')
    ) AS p2(nome)
    CROSS JOIN (VALUES
        ('Silva'),('Santos'),('Oliveira'),('Souza'),('Costa'),('Pereira'),('Rodrigues'),('Almeida'),('Lima'),('Gomes'),
        ('Ribeiro'),('Carvalho'),('Rocha'),('Barbosa'),('Martins'),('Ferreira'),('Batista'),('Correia'),('Teixeira'),('Araújo'),
        ('Melo'),('Nogueira'),('Cardoso'),('Moreira'),('Andrade'),('Freitas'),('Campos'),('Vieira'),('Monteiro'),('Mendes')
    ) AS s1(sobrenome)
    ORDER BY random()
),
base AS (
    SELECT rn, titular
    FROM nomes
    LIMIT 220
),
contas AS (
    SELECT
        rn,
        titular,
        ((rn - 1) % 40) + 1 AS agencia,                                  -- 1..40
        100000 + rn AS numero,                                            -- 100001..100220 (único)
        ROUND((random() * 150000 - 5000)::numeric, 2) AS saldo,            -- -5000.00 .. 145000.00
        (18 + floor(random() * 63))::int AS idade,                         -- 18..80
        lpad((10000000000 + rn)::text, 11, '0') AS cpf,                    -- CPF único (11 dígitos)
        (ARRAY['GOLD','PLATINUM','BLACK'])[(floor(random() * 3) + 1)::int] AS tipo
    FROM base
)
INSERT INTO tb_conta (titular, agencia, numero, saldo, cpf, idade, tipo)
SELECT
    titular, agencia, numero, saldo, cpf, idade, tipo
FROM contas;
