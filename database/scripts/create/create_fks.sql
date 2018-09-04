PROMPT 'Criando chave estrangeira cont_inte_ofer_fk.'
ALTER TABLE contratos
ADD CONTRAINT cont_inte_ofer_fk
FOREIGN KEY (inte_id_oferta)
REFERENCES intencoes (id);

PROMPT 'Criando chave estrangeira cont_inte_dema_fk.'
ALTER TABLE contratos
ADD CONTRAINT cont_inte_dema_fk
FOREIGN KEY (inte_id_demanda)
REFERENCES intencoes (id);

PROMPT 'Criando chave estrangeira esto_prod_fk.'
ALTER TABLE estoque
ADD CONTRAINT esto_prod_fk
FOREIGN KEY (prod_id)
REFERENCES produtos (id);

PROMPT 'Criando chave estrangeira esto_empr_fk.'
ALTER TABLE estoque
ADD CONTRAINT esto_empr_fk
FOREIGN KEY (empr_id)
REFERENCES empresas (id);

PROMPT 'Criando chave estrangeira emin_prod_fk.'
ALTER TABLE empresas_intencoes 
ADD CONTRAINT emin_prod_fk
FOREIGN KEY (prod_id)
REFERENCES produtos (id);


PROMPT 'Criando chave estrangeira emin_empr_fk.'
ALTER TABLE empresas_intencoes
ADD CONSTRAINT emin_empr_fk
FOREIGN KEY (empr_id)
REFERENCES empresas (id);


