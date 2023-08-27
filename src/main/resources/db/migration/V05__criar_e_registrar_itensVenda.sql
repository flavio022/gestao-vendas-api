CREATE TABLE item_venda (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	codigo_produto BIGINT NOT NULL,
	codigo_venda BIGINT NOT NULL,
	quantidade INTEGER NOT NULL,
	preco_vendido DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (codigo_produto) REFERENCES produto(codigo),
	FOREIGN KEY (codigo_venda) REFERENCES venda(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

