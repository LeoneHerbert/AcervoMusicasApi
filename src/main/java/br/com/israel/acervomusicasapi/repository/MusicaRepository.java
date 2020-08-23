package br.com.israel.acervomusicasapi.repository;

import br.com.israel.acervomusicasapi.models.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.israel.acervomusicasapi.models.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer>{
    Page<Musica> findByNome(String nomeMusica, Pageable paginacao);

    Page<Musica> findByPlaylistMusica_Playlist(Playlist playlist, Pageable paginacao);
}
