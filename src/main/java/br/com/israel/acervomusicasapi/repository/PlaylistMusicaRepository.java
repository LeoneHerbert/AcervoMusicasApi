package br.com.israel.acervomusicasapi.repository;

import br.com.israel.acervomusicasapi.models.PlaylistMusica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistMusicaRepository extends JpaRepository<PlaylistMusica, Integer> {
    @Query("DELETE PlaylistMusica pm WHERE pm.playlist.id = :idPlaylist AND pm.musica.id = :idMusica")
    void deleteMusicaPlaylist(@Param("idPlaylist") int idPlaylist, @Param("idMusica") int idMusica);
}
