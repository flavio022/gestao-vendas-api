CREATE TABLE venda (
	  codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
  	data DATE NOT NULL,
  	codigo_cliente BIGINT NOT NULL,
  	FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo)
  ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
