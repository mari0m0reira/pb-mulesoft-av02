CREATE DATABASE pb_quiz;

CREATE TABLE resultados (
	id INT NOT NULL AUTO_INCREMENT,
    jogador VARCHAR(100),
    acertos INT,
    erros INT,
    data_da_partida DATE,
    PRIMARY KEY (ID)
);

CREATE TABLE questoes(
	id INT NOT NULL AUTO_INCREMENT,
    pergunta VARCHAR(300),
    verdadeira BOOLEAN,
    ativa BOOLEAN,
    PRIMARY KEY (ID)
);

INSERT INTO questoes
(pergunta, verdadeira, ativa)
VALUES
('O beija-flor-abelha é o menor passaro do mundo.', true, true),
('1 + 1 = 7', false, true),
('O maior rio do mundo é o rio Amazonas.', true, true),
('A Rainha Elizabeth II faleceu no ano de 2023.', false, true),
('A capital do Brasil é Bueno Aires.', false, true),
('7 x 8 = 42.', false, false),
('A seleção Brasileira de Futebol ganhou 5 copas mundiais.', true, false),
('False AND true = true.', false, true),
('True OR false = true.', true, true),
('O simbolo do JAVA é a xícara de café', true, true),
('O sol é considerado um planeta.', false, true),
('A lua é considerada um satelite.', true, true);
	
