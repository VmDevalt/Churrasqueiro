
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

ALTER TABLE usuario ALTER COLUMN id RESTART WITH 3;