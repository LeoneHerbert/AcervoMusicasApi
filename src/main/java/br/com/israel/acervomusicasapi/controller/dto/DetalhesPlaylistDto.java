package br.com.israel.acervomusicasapi.controller.dto;

import br.com.israel.acervomusicasapi.models.Playlist;
import org.springframework.data.domain.Page;

public class DetalhesPlaylistDto {
    private Integer id;
    private String nome;

    public DetalhesPlaylistDto(Playlist playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static Page<DetalhesPlaylistDto> converter(Page<Playlist> playlist) {
        return playlist.map(DetalhesPlaylistDto::new);
    }
}
