
MERGE INTO usuario 
KEY (login) 
VALUES (
    1, 
    'admin', 
    '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 
    'ADMIN', 
    'admin@churrasqueiro.com'
);

MERGE INTO usuario 
KEY (login) 
VALUES (
    2, 
    'funcionario', 
    '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 
    'ATENDENTE', 
    'atendente@churrasqueiro.com'
);

MERGE INTO usuario 
KEY (login) 
VALUES (
    3, 
    'funcionario4', 
    '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 
    'ATENDENTE', 
    'atendente@churrasqueiro.com'
);

MERGE INTO usuario 
KEY (login) 
VALUES (
    4, 
    'funcionario2', 
    '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 
    'ATENDENTE', 
    'atendente@churrasqueiro.com'
);

INSERT INTO Mesa (numeroMesa)
VALUES
(1),
(2),
(3);

INSERT INTO Caixa (saldo_inicial, saldo_atual, meta_faturamento, data_abertura, data_fechamento, status_caixa, vendas_dia, vendas_meta)
VALUES
(1000.00, 1000.00, 5000.00, '2025-11-01 08:00:00', NULL, TRUE, 1500.00, 5000.00),
(2000.00, 2000.00, 10000.00, '2025-11-01 09:00:00', '2025-11-01 18:00:00', FALSE, 9000.00, 10000.00),
(1500.00, 1500.00, 7000.00, '2025-11-01 10:00:00', '2025-11-01 17:00:00', FALSE, 6000.00, 7000.00),
(1200.00, 1200.00, 6000.00, '2025-11-01 11:00:00', '2025-11-01 19:00:00', FALSE, 5500.00, 6000.00),
(1800.00, 1800.00, 8000.00, '2025-11-01 12:00:00', '2025-11-01 20:00:00', FALSE, 7600.00, 8000.00);


INSERT INTO Categorias_Cardapio (nome, descricao)
VALUES
('Hambúrguer', 'Categoria de hambúrgueres gourmet, como hambúrgueres 4 queijos, picanha, etc.'),
('Bebidas', 'Categoria de bebidas, incluindo refrigerantes, sucos e bebidas alcoólicas.'),
('Acompanhamentos', 'Categoria de acompanhamentos, como batatas fritas, arroz, feijão, etc.'),
('Pratos Principais', 'Categoria de pratos principais, como carne, frango, peixe, etc.'),
('Sobremesas', 'Categoria de sobremesas, como pudim, bolo, torta, etc.');

INSERT INTO Itens_Cardapio (nome, descricao, preco, categoria_id, foto_url, preco_comparacao)
VALUES
('Cheeseburger', 'Hambúrguer com queijo, alface, tomate e molho especial', 18.50, 1, 'imagem_cheeseburger.jpg', 22.00),
('Refrigerante Lata', 'Refrigerante Coca-Cola, lata de 350ml', 4.00, 2, 'imagem_refrigerante.jpg', 5.00),
('Batata Frita', 'Batatas fritas crocantes, servidas com molho', 7.50, 3, 'imagem_batata_frita.jpg', 9.00),
('Frango Grelhado', 'Peito de frango grelhado com legumes', 25.00, 4, 'imagem_frango_grelhado.jpg', 28.00),
('Pudim de Leite', 'Sobremesa pudim de leite condensado', 6.00, 5, 'imagem_pudim.jpg', 8.00);

INSERT INTO Pedidos (mesa_id, garcon_id, data_hora, status, desconto, acrescimo, total, forma_pagamento)
VALUES
(1, 1, '2025-11-01 12:30:00', 'Aberto', 2.00, 0.00, 20.50, 'Cartão de Crédito'),
(2, 2, '2025-11-01 13:00:00', 'Aberto', 1.00, 1.50, 25.00, 'Dinheiro'),
(3, 3, '2025-11-01 14:00:00', 'Aberto', 0.00, 2.00, 18.00, 'Pix'),
(1, 2, '2025-11-01 15:00:00', 'Aberto', 1.50, 0.00, 22.00, 'Cartão de Débito'),
(2, 1, '2025-11-01 16:00:00', 'Aberto', 0.00, 1.00, 26.00, 'Dinheiro');

INSERT INTO Itens_Pedido (pedido_id, item_cardapio_id, quantidade, preco_unitario, total_item)
VALUES
(1, 1, 1, 18.50, 18.50),
(1, 3, 1, 7.50, 7.50),
(2, 2, 1, 4.00, 4.00),
(2, 4, 1, 25.00, 25.00), 
(3, 1, 1, 18.50, 18.50),
(4, 5, 1, 6.00, 6.00), 
(5, 2, 1, 4.00, 4.00),
(5, 3, 1, 7.50, 7.50); 


ALTER TABLE usuario ALTER COLUMN id RESTART WITH 3;