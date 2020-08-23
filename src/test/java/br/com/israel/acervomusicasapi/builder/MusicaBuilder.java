package br.com.israel.acervomusicasapi.builder;

import br.com.israel.acervomusicasapi.models.Musica;

public class MusicaBuilder {
    private Musica musica;

    public MusicaBuilder() {
    }

    public static MusicaBuilder umaMusica() {
        MusicaBuilder builder = new MusicaBuilder();

        builder.musica = new Musica();
        builder.musica.setNome("Rosa de Saron Mero Poema");
        builder.musica.setUrl("https://www.youtube.com/watch?v=gndri63K7U4");

        return builder;
    }

    public MusicaBuilder comUrlVazia() {
        this.musica.setUrl("");
        return this;
    }

    public MusicaBuilder semNome() {
        this.musica.setNome("");
        return this;
    }

    public MusicaBuilder comNome(String nome) {
        this.musica.setNome(nome);
        return this;
    }

    public Musica constroi() {
        return this.musica;
    }

}
