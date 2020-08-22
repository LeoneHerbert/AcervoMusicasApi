package br.com.israel.acervomusicasapi.controller.dto;

import br.com.israel.acervomusicasapi.models.Musica;
import org.springframework.data.domain.Page;

public class DetalhesMusicaDto {
    private Integer id;
    private String nome;
    private String url;

    public DetalhesMusicaDto(Musica musica) {
        this.id = musica.getId();
        this.nome = musica.getNome();
        this.url = musica.getUrl();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUrl() {
        return url;
    }

    public static Page<DetalhesMusicaDto> converter(Page<Musica> musicas) {
        return musicas.map(DetalhesMusicaDto::new);
    }
}
