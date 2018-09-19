PROMPT 'Criando chave única cont_uk.'
ALTER TABLE contratos
ADD CONSTRAINT cont_uk
UNIQUE 
(
  inte_id_oferta, 
  inte_id_demanda
);

PROMPT 'Criando chave única empr_uk.'
ALTER TABLE empresas
ADD CONSTRAINT empr_uk
UNIQUE 
(
  part_id,
  nome
);

PROMPT 'Criando chave única prod_uk.'
ALTER TABLE produtos
ADD CONSTRAINT prod_uk
UNIQUE 
(
  part_id,
  nome
);
