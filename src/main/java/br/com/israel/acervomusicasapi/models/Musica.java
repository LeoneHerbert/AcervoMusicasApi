package br.com.israel.acervomusicasapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 255, nullable = false)
    private String url;

    public Musica(String nome, String url) {
		this.nome = nome;
		this.url = url;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return Objects.equals(id, musica.id) &&
                Objects.equals(nome, musica.nome) &&
                Objects.equals(url, musica.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, url);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
