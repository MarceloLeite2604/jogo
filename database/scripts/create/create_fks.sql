PROMPT 'Criando chave estrangeira cont_inte_ofer_fk.'
ALTER TABLE contratos
ADD CONSTRAINT cont_inte_ofer_fk
FOREIGN KEY (inte_id_oferta)
REFERENCES intencoes (id);

PROMPT 'Criando chave estrangeira cont_inte_dema_fk.'
ALTER TABLE contratos
ADD CONSTRAINT cont_inte_dema_fk
FOREIGN KEY (inte_id_demanda)
REFERENCES intencoes (id);

PROMPT 'Criando chave estrangeira ites_prod_fk.'
ALTER TABLE itens_estoque 
ADD CONSTRAINT ites_prod_fk
FOREIGN KEY (prod_id)
REFERENCES produtos (id);

PROMPT 'Criando chave estrangeira ites_empr_fk.'
ALTER TABLE itens_estoque
ADD CONSTRAINT ites_empr_fk
FOREIGN KEY (empr_id)
REFERENCES empresas (id);

PROMPT 'Criando chave estrangeira inte_empr_fk.'
ALTER TABLE intencoes
ADD CONSTRAINT inte_empr_fk
FOREIGN KEY (empr_id)
REFERENCES empresas (id);

PROMPT 'Criando chave estrangeira inte_prod_fk.'
ALTER TABLE intencoes
ADD CONSTRAINT inte_prod_fk
FOREIGN KEY (prod_id)
REFERENCES produtos (id);
