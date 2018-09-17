PROMPT 'Criando chave única cont_uk.'
ALTER TABLE contratos
ADD CONSTRAINT cont_uk
UNIQUE 
(
  inte_id_oferta, 
  inte_id_demanda
);
