PROMPT 'Excluindo conteúdo de itens_estoque.'
DELETE FROM itens_estoque;

PROMPT 'Excluindo conteúdo de contratos.'
DELETE FROM contratos;

PROMPT 'Excluindo conteúdo de intencoes.'
DELETE FROM intencoes;

PROMPT 'Excluindo conteúdo de produtos.'
DELETE FROM produtos;

PROMPT 'Excluindo conteúdo de empresas.'
DELETE FROM empresas;

PROMPT 'Excluindo conteúdo de partidas.'
DELETE FROM partidas;

COMMIT;