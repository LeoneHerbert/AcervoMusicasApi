CREATE TABLE usuario(
    id  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    CONSTRAINT pk_id_usuario PRIMARY KEY (id)
)engine=InnoDB DEFAULT CHARSET=utf8;