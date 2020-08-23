package br.com.israel.acervomusicasapi.controller.form;

import br.com.israel.acervomusicasapi.models.Playlist;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PlaylistForm {
    @NotEmpty
    @Size(min = 1, max = 50)
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Playlist converter() {
        return new Playlist(this.nome);
    }
}
