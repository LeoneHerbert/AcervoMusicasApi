package br.com.israel.acervomusicasapi.controller.dto;

import br.com.israel.acervomusicasapi.models.Musica;

public class MusicaDto {
	private Integer id;
    private String nome;

    public MusicaDto(Musica musica) {
        this.id = musica.getId();
        this.nome = musica.getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
