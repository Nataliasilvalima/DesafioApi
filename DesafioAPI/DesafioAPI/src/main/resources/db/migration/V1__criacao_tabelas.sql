CREATE TABLE tb_usuario(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(255) NOT NULL,
email varchar(255) NOT NULL,
password varchar(255) NOT NULL);

CREATE TABLE tb_perfis(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome varchar(255) NOT NULL);

CREATE TABLE tb_etiqueta(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL);

CREATE TABLE tb_usuario_perfil(
usuario_id BIGINT,
perfil_id  BIGINT,
FOREIGN KEY (perfil_id) REFERENCES tb_perfis(Id),
FOREIGN KEY (usuario_id) REFERENCES tb_usuario(Id));






 