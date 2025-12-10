
MERGE INTO usuario 
KEY (login) 
VALUES (
    1, 
    'admin', 
    '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 
    'ADMIN', 
    'admin@churrasqueiro.com',
    '1111',
    '2025-11-05 10:15:04'

);

MERGE INTO usuario 
KEY (login) 
VALUES (
    2, 
    'funcionario', 
    '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 
    'ATENDENTE', 
    'atendente@churrasqueiro.com',
    '2222',
    '2025-11-05 10:15:04'
);

MERGE INTO usuario 
KEY (login) 
VALUES (
    3, 
    'funcionario4', 
    '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 
    'ATENDENTE', 
    'atendente@churrasqueiro.com',
    '3333',
    '2025-11-05 10:15:04'

);

MERGE INTO usuario 
KEY (login) 
VALUES (
    4, 
    'funcionario2', 
    '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 
    'ATENDENTE', 
    'atendente@churrasqueiro.com',
    '4444',
    '2025-11-05 10:15:04'

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
('Bebidas', 'Categoria de bebidas, incluindo refrigerantes, sucos, água, etc.'),
('Acompanhamentos', 'Categoria de acompanhamentos, como batatas fritas, coxinhas, onions, etc.'),
('Sobremesas', 'Categoria de sobremesas, como pudim, bolo, milk-shake, etc.'),
('Gourmet', 'Categoria dos gourmets, os mais simples.'),
('Especial', 'Categoria dos especiais, os mais classudos.'),
('Supremo', 'Categoria dos supremos, os mais completos.'),
('Combos', 'Categoria de combos, combos individuas, combos que servem até duas pessoas, três pessoas, ou quatro pessoas');

INSERT INTO Itens_Cardapio (nome, descricao, preco, categoria_id, foto_url, preco_comparacao)
VALUES

-- SUPREMO (categoria_id = 6)
('Super Triplo', 'Pão brioche, 3 blend de 120g na brasa, creme cheese, gorgonzola, cheddar, 3 fatias de bacon, cebola caramelizada e maionese da casa', 30.00, 6, 'SUPER-TRIPLO.jpg', 55.00),
('Super Bacon', 'Pão Brioche, 2 Blend de 120g na brasa, Mussarela, Cheddar, 4 Fatias de bacon, Gorgonzola, Cebola caramelizada e Maionese da casa', 30.00, 6, 'SUPER-BACON.jpg', 55.00),
('Duplo Cheddar', 'Pão brioche, 2 Blend de 120g na brasa, 2 camadas de cheddar, 4 fatias de bacon, Cebola caramelizada e Maionese da casa', 30.00, 6, 'DUPLO-CHEEDAR.jpg', 55.00),
('Maminha', 'Pão brioche, hambúrguer de maminha 120g na brasa, mussarela, bacon, queijo coalho, crisp de parmesão, onion rings, picles e maionese da casa', 30.00, 6, 'MAMINHA.jpg', 55.00),
('X-Tudo', 'Pão brioche, 2 Blend de 120g na brasa, Calabresa, Mussarela, Ovo, Salsicha, Bacon, Cebola caramelizada e Maionese da casa', 30.00, 6, 'X-TUDO.jpg', 55.00),

-- ESPECIAL (categoria_id = 5)
('Moda da Casa', 'Pão brioche, Blend de 120g na brasa, Mussarela, Cheddar, Bacon, Geleia de frutas vermelhas, Cebola caramelizada e Maionese da casa', 25.00, 5, 'MODA-DA-CASA.jpg', 45.00),
('Gorgonzola Especial', 'Pão brioche, Blend de 120g na brasa, Mussarela, Creme de gorgonzola, Bacon, Geleia de pimenta, Cebola caramelizada e Maionese da casa', 25.00, 5, 'GORGONZOLA-ESPECIAL.jpg', 45.00),
('Camarão Especial', 'Pão brioche, Blend de 120g na brasa, Mussarela, Creme de gorgonzola, Picles, Cebola caramelizada, Camarão e Maionese da casa', 25.00, 5, 'CAMARÃO-ESPECIAL.jpg', 45.00),
('Creme Cheese Especial', 'Pão brioche, Blend de 120g na brasa, Mussarela, Creme Cheese, Bacon, Cebola caramelizada e Maionese da casa', 25.00, 5, 'CREME-CREESE-ESPECIAL.jpg', 45.00),
('Moda do Chefe', 'Pão brioche, Blend de 120g na brasa, Mussarela, Cheddar, Bacon, Cebola Crispy e Maionese da casa', 25.00, 5, 'MODA-DO-CHEFE.jpg', 45.00),
('Frango Especial', 'Pão brioche, Blend de 120g na brasa, Ovo frito, Mussarela, Frango desfiado, Bacon, Rúcula, Tomate, Cebola caramelizada e Maionese da casa', 25.00, 5, 'FRANGO-ESPECIAL.jpg', 45.00),
('Costela', 'Pão brioche, Hambúrguer de costela 120g na brasa, Mussarela, Creme de gorgonzola, Bacon, Onion rings e Maionese da casa', 25.00, 5, 'COSTELA.jpg', 45.00),
('Matuto', 'Pão brioche, Blend de 120g na brasa, Mussarela, Coalho, Carne de sol, Bacon, Cebola caramelizada e Maionese da casa', 25.00, 5, 'MATUTO.jpg', 45.00),
('Sertanejo', 'Pão brioche, Blend de 120g na brasa, Mussarela, Coalho, Charque, Bacon, Cebola caramelizada e Maionese da casa', 25.00, 5, 'SERTANEJO.jpg', 45.00),

-- GOURMET (categoria_id = 4)
('Cheddar Burguer', 'Pão Brioche, Blend de 120g na brasa, Cheddar, Maionese da casa e Cebola caramelizada', 18.00, 4, 'CHEDDAR-BURGUER.jpg', 28.00),
('Gorgonzola', 'Pão Brioche, Blend de 120g na brasa, Creme de gorgonzola, Maionese da casa e Cebola caramelizada', 18.00, 4, 'GORGONZOLA.jpg', 28.00),
('Creme Cheese', 'Pão Brioche, Blend de 120g na brasa, Creme cheese, Maionese da casa e Cebola caramelizada', 18.00, 4, 'CREME-CREESE.jpg', 28.00),
('Coalho', 'Pão Brioche, Blend de 120g na brasa, Coalho, Maionese da casa e Cebola caramelizada', 18.00, 4, 'COALHO.jpg', 28.00),
('Mussarela', 'Pão Brioche, Blend de 120g na brasa, Mussarela, Maionese da casa e Cebola caramelizada', 18.00, 4, 'MUSSARELA.jpg', 28.00),
('American Cheese', 'Pão Brioche, Blend de 120g na brasa, Queijo cheddar em fatias, Maionese da casa e Cebola caramelizada', 18.00, 4, 'AMERICAN-CHEESE.jpg', 28.00),
('Romeu e Julieta', 'Pão Brioche, Blend de 120g na brasa, queijo coalho, goiabada, Maionese da casa e Cebola caramelizada', 18.00, 4, 'ROMEU-E-JULIETA.jpg', 28.00),
('Pão com carne', 'Pão Brioche, Blend de 120g na brasa, Maionese da casa e Cebola caramelizada', 18.00, 4, 'PÃO-COM-CARNE.jpg', 28.00),
('2 Queijos (Mussarela e Coalho)', 'Pão Brioche, Blend de 120g na brasa, Mussarela e Coalho, Maionese da casa e Cebola caramelizada', 18.00, 4, 'DOIS-QUEIJOS-MUSSARELA-E-QUALHO.jpg', 28.00),
('Frango Gourmet', 'Pão Brioche, 200g de Frango Desfiado, Queijo mussarela, Maionese da Casa e cebola caramelizada', 18.00, 4, 'FRANGO-GOURMET.jpg', 28.00),

-- COMBOS (categoria_id = 7)
('Combo dos amigos', '3 hambúrgueres da categoria gourmet; porção de batata de 200g; refrigerante de 1L', 60.00, 7, 'COMBO-DOS-AMIGOS.jpg', 75.00),
('Combo Casal', 'Escolha 2 hambúrgueres gourmet + batata frita de 200g + 2 refrigerantes lata', 45.00, 7, 'COMBO-CASAL.jpg', 60.00),
('Combo Gourmet', 'Um Hamburguer Gourmet + Batata Frita 100g + Guaraná em lata', 30.00, 7, 'COMBO-GOURMET.jpg', 40.00),
('Combo Família', '4 Hambúrgueres Gourmet + 2 porção de Batata Frita 150g + onion rings + Coca 1,5L', 80.00, 7, 'COMBO-FAMILIA.jpg', 110.00),
('Combo Supremo', '1 Hamburguer Supremo + Batata Frita 100g + refrigerante em lata', 38.00, 7, 'COMBO-SUPREMO.jpg', 45.00),
('Combo Especial', '1 Hamburguer Especial + Batata Frita 100g + Guaraná em lata', 35.00, 7, 'COMBO-ESPECIAL.jpg', 42.00),

-- PORÇÕES (categoria_id = 2)
('Batata frita com frango desfiado 400g', 'Frango desfiado, queijo opcional, Cheddar ou Gorgonzola ou Creme cheese e queijo ralado', 25.00, 2, 'BATATA-FRANGO-BACON.jpg', 30.00),
('Batata frita com costela no bafo 400g', 'Costela no bafo, queijo opcional, Cheddar ou Gorgonzola ou Creme cheese e queijo ralado', 25.00, 2, 'BATATA-COM-COSTELA.jpg', 35.00),
('Batata Frita 300g', 'Porção de batata frita crocante de 300g', 15.00, 2, 'BATATA.jpg', 32.00),
('Batata frita com bacon 350g', 'Queijo opcional, Cheddar ou Gorgonzola ou Creme cheese', 22.00, 2, 'BATATA-COM-BACON.jpg', 35.00),
('10 Onion Rings', 'Porção com 10 unidades de onion rings com molho especial', 15.00, 2, 'PORÇÃO-DE-ONIONS.jpg', 38.00),
('Porção de Nugget', 'Porção com 6 unidades de nuggets', 15.00, 2, 'PORÇÃO-DE-NUGGTES.jpg', 38.00),

-- BEBIDAS (categoria_id = 1)
('Coca Cola 1,5L', 'Coca-Cola gelada 1,5 litros', 15.00, 1, 'COCA-COLA-1,5L.jpg', 18.00),
('Coca Cola 1L', 'Coca-Cola gelada 1 litro', 12.00, 1, 'COCA-COLA-1L.jpg', 15.00),
('Antártica 1L', 'Antártica geladíssima 1L', 10.00, 1, 'ANTARTICA-1L.jpg', 13.00),
('Pepsi 1L', 'Pepsi gelada 1L', 10.00, 1, 'PEPSI-1L.jpg', 13.00),
('Pepsi Zero 1L', 'Pepsi Zero 1L', 10.00, 1, 'PEPSI-ZERO-1L.jpg', 13.00),
('Coca Cola Lata', 'Coca-Cola lata gelada', 7.00, 1, 'COCA-COLA-LATA.jpg', 10.00),
('Coca-Zero Lata', 'Coca Zero lata', 7.00, 1, 'COCA-COLA-ZERO-LATA.jpg', 10.00),
('Fanta Lata', 'Fanta lata gelada', 7.00, 1, 'FANTA-LATA.jpg', 10.00),
('Pepsi Lata', 'Pepsi lata gelada', 7.00, 1, 'PEPSI-LATA.jpg', 10.00),
('Antártica Lata', 'Antártica em lata', 7.00, 1, 'ANTÁRTICA-LATA.jpg', 10.00),
('Sprite Lata', 'Sprite lata', 7.00, 1, 'SPRITE-LATA.jpg', 10.00),
('Soda Lata', 'Soda lata', 7.00, 1, 'SODA-LATA.jpg', 10.00),
('Coca Cola Mini', 'Coca-Cola mini', 5.00, 1, 'COCA-COLA-MINI.jpg', 8.00),
('Del Valle Laranja', 'Suco Del Valle sabor laranja', 7.00, 1, 'DELL-VALLE-LARANJA.jpg', 10.00),
('Água', 'Água sem gás', 3.50, 1, 'AGUA-SEM-GAS.jpg', 5.00),
('Água com Gás', 'Água com gás', 4.00, 1, 'AGUA-COM-GAS.jpg', 5.00),

-- SOBREMESA (categoria_id = 3)
('Milk Shake 400ml', 'Milk shake de Chocolate, Morango ou Ovomaltine', 15.00, 3, 'MILK-SHAKE.jpg', 20.00),
('Pudim de Leite', 'Sobremesa pudim de leite condensado', 6.00, 3, 'imagem_pudim.jpg', 8.00);

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


ALTER TABLE usuario ALTER COLUMN id RESTART WITH 10;
ALTER TABLE Pedidos ALTER COLUMN id RESTART WITH 10;