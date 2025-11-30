CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL,
    senhaHash VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS categorias_cardapio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS itens_cardapio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    categoria_id INT NOT NULL,
    foto_url VARCHAR(255),
    preco_comparacao DECIMAL(10, 2),
    FOREIGN KEY (categoria_id) REFERENCES categorias_cardapio(id)
);

CREATE TABLE IF NOT EXISTS pedidos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mesa_id INT NOT NULL,
    garcon_id INT,
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    desconto DECIMAL(10, 2) DEFAULT 0.00,
    acrescimo DECIMAL(10, 2) DEFAULT 0.00,
    total DECIMAL(10, 2) NOT NULL,
    forma_pagamento VARCHAR(50) NOT NULL,
    FOREIGN KEY (garcon_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS itens_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pedido_id INT,
    item_cardapio_id INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    total_item DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (item_cardapio_id) REFERENCES itens_cardapio(id)
);

CREATE TABLE IF NOT EXISTS Caixa (
    id INT AUTO_INCREMENT PRIMARY KEY,          
    saldo_inicial DECIMAL(10, 2) NOT NULL,       
    saldo_atual DECIMAL(10, 2) NOT NULL,        
    meta_faturamento DECIMAL(10, 2) NOT NULL,   
    data_abertura DATETIME NOT NULL,             
    data_fechamento DATETIME,                    
    status_caixa BOOLEAN NOT NULL,              
    vendas_dia DECIMAL(10, 2) DEFAULT 0.00,      
    vendas_meta DECIMAL(10, 2) NOT NULL          
);

CREATE TABLE IF NOT EXISTS Mesa (
    id INT AUTO_INCREMENT PRIMARY KEY,       
    numeroMesa INT AUTO_INCREMENT    
);
