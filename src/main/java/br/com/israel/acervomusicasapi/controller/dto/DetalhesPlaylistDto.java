package br.com.israel.acervomusicasapi.controller.dto;

import br.com.israel.acervomusicasapi.models.Playlist;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesPlaylistDto {
    private Integer id;
    private String nome;
    private List<DetalhesMusicaDto> musicas;

    public DetalhesPlaylistDto(Playlist playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();

        this.musicas = new ArrayList<>();
        this.musicas.addAll(playlist.getMusicas().stream().map(DetalhesMusicaDto::new).collect(Collectors.toList()));
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<DetalhesMusicaDto> getMusicas() {
        return musicas;
    }

    public static Page<DetalhesPlaylistDto> converter(Page<Playlist> playlist) {
        return playlist.map(DetalhesPlaylistDto::new);
    }
}
