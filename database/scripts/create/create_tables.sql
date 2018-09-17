PROMPT 'Criando tabela empresa.'
CREATE TABLE empresas
(
    id NUMBER(10) NOT NULL,
    nome VARCHAR2(64 CHAR) NOT NULL,
    tipo NUMBER(1) NOT NULL,
    caixa NUMBER(20,5) NOT NULL
);

PROMPT 'Criando tabela produtos.'
CREATE TABLE produtos
(
    id NUMBER(10) NOT NULL,
    nome VARCHAR2(64 CHAR) NOT NULL
);

PROMPT 'Criando tabela intencoes.'
CREATE TABLE intencoes
(
    id NUMBER(10) NOT NULL,
    empr_id NUMBER(10) NOT NULL,
    prod_id NUMBER(10) NOT NULL,
    tipo NUMBER(1) NOT NULL,
    status NUMBER(1) NOT NULL,
    preco_unitario NUMBER(20,5) NOT NULL,
    quantidade NUMBER(20,5) NOT NULL
);

PROMPT 'Criando tabela contratos.'
CREATE TABLE contratos
(
    id NUMBER(10) NOT NULL,
    inte_id_oferta NUMBER(10) NOT NULL,
    inte_id_demanda NUMBER(10) NOT NULL,
    quantidade NUMBER(22,5) NOT NULL,
    preco_unitario NUMBER(20,5) NOT NULL,
    tipo_intencao_geradora NUMBER(1) NOT NULL
);

PROMPT 'Criando tabela itens_estoque.'
CREATE TABLE itens_estoque(
    empr_id NUMBER(10) NOT NULL,
    prod_id NUMBER(10) NOT NULL,
    quantidade NUMBER(20,5) NOT NULL
);
