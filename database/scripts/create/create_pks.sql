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

PROMPT 'Criando chave primária esto_pk.'
ALTER TABLE estoque 
ADD CONSTRAINT esto_pk
PRIMARY KEY
(
    prod_id,
    empr_id
);

PROMPT 'Criando chave primária emin_pk.'
ALTER TABLE empresas_intencoes
ADD CONTRAINT emin_pk
(
    empr_id,
    inte_id
);
