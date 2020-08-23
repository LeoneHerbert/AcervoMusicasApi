CREATE TABLE playlist_musica(
    id  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    playlist_id INT(11) UNSIGNED NOT NULL,
    musica_id INT(11) UNSIGNED NOT NULL,
    CONSTRAINT pk_id_playlist_musca PRIMARY KEY (id),
    CONSTRAINT fk_id_playlist FOREIGN KEY (playlist_id) REFERENCES playlist(id),
    CONSTRAINT fk_id_musica FOREIGN KEY (musica_id) REFERENCES musica(id)
)engine=InnoDB DEFAULT CHARSET=utf8;