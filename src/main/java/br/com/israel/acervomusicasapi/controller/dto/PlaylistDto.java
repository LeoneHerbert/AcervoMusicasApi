package br.com.israel.acervomusicasapi.controller.dto;

import br.com.israel.acervomusicasapi.models.Playlist;

public class PlaylistDto {
    private Integer id;
    private String nome;

    public PlaylistDto(Playlist playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
