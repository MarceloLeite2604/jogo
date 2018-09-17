PROMPT 'Criando chave primária empr_pk.'
ALTER TABLE empresas
ADD CONSTRAINT empr_pk
PRIMARY KEY (id);

PROMPT 'Criando chave primária prod_pk.'
ALTER TABLE produtos
ADD CONSTRAINT prod_pk
PRIMARY KEY (id);

PROMPT 'Criando chave primária inte_pk.'
ALTER TABLE intencoes 
ADD CONSTRAINT inte_pk
PRIMARY KEY (id);

PROMPT 'Criando chave primária cont_pk.'
ALTER TABLE contratos 
ADD CONSTRAINT cont_pk
PRIMARY KEY (id);

PROMPT 'Criando chave primária ites_pk.'
ALTER TABLE itens_estoque 
ADD CONSTRAINT ites_pk
PRIMARY KEY
(
    prod_id,
    empr_id
);
