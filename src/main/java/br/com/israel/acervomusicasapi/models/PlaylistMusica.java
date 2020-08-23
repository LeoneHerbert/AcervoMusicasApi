package br.com.israel.acervomusicasapi.models;

import javax.persistence.*;

@Entity
@Table(name = "playlist_musica")
public class PlaylistMusica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "musica_id")
    private Musica musica;

    public PlaylistMusica() {
    }

    public PlaylistMusica(Playlist playlist, Musica musica) {
        this.playlist = playlist;
        this.musica = musica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
}
