package br.com.israel.acervomusicasapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.israel.acervomusicasapi.models.Musica;


public class MusicaForm {
	@NotEmpty @Size(min = 1, max = 50)
    private String nome;
    
    @Size(min = 5)
    private String url;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Musica converter() {
        return new Musica(this.nome, this.url);
    }
}
