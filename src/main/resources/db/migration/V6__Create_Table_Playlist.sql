CREATE TABLE playlist(
    id  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    id_usuario INT(11) UNSIGNED NOT NULL,
    CONSTRAINT pk_id_playlist PRIMARY KEY (id),
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
)engine=InnoDB DEFAULT CHARSET=utf8;