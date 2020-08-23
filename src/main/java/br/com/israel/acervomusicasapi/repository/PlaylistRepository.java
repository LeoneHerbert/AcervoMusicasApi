package br.com.israel.acervomusicasapi.repository;

import br.com.israel.acervomusicasapi.models.Playlist;
import br.com.israel.acervomusicasapi.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    Page<Playlist> findAllByUsuario(Usuario usuario, Pageable paginacao);
}
