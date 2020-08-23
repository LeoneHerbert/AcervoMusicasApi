package br.com.israel.acervomusicasapi.service;

import br.com.israel.acervomusicasapi.config.security.TokenService;
import br.com.israel.acervomusicasapi.models.Musica;
import br.com.israel.acervomusicasapi.models.Playlist;
import br.com.israel.acervomusicasapi.models.PlaylistMusica;
import br.com.israel.acervomusicasapi.models.Usuario;
import br.com.israel.acervomusicasapi.repository.MusicaRepository;
import br.com.israel.acervomusicasapi.repository.PlaylistMusicaRepository;
import br.com.israel.acervomusicasapi.repository.PlaylistRepository;
import br.com.israel.acervomusicasapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private PlaylistMusicaRepository playlistMusicaRepository;

    @Transactional
    public Playlist salvar(Playlist playlist, String token) {
        Integer idUsuario = tokenService.getIdUsuario(tokenService.retornarTokenSemBearer(token));
        Usuario usuario = usuarioRepository.getOne(idUsuario);
        playlist.setUsuario(usuario);

        return playlistRepository.save(playlist);
    }

    @Transactional(readOnly = true)
    public Optional<Playlist> buscarPor(Integer id) {
        return playlistRepository.findById(id);
    }

    @Transactional
    public PlaylistMusica adicionarMusica(Playlist playlist, Integer idMusica) {
        Musica musica = musicaRepository.getOne(idMusica);
        PlaylistMusica playlistMusica = new PlaylistMusica(playlist, musica);

        return playlistMusicaRepository.save(playlistMusica);
    }

    @Transactional(readOnly = true)
    public Page<Playlist> buscarPorUsuario(String token, Pageable paginacao) {
        Integer idUsuario = tokenService.getIdUsuario(tokenService.retornarTokenSemBearer(token));
        Usuario usuario = usuarioRepository.getOne(idUsuario);

        return playlistRepository.findAllByUsuario(usuario, paginacao);
    }

    @Transactional
    public void excluir(Integer id) {
        playlistRepository.deleteById(id);
    }

    @Transactional
    public Playlist editar(Playlist playlistAtualizacao, Playlist playlistNovosDados) {
        playlistAtualizacao.setNome(playlistNovosDados.getNome());
        return playlistRepository.save(playlistAtualizacao);
    }

    @Transactional
    public void excluirMusica(Playlist playlist, Integer idMusica) {
        Musica musica = musicaRepository.getOne(idMusica);
        playlistMusicaRepository.deleteMusicaPlaylist(playlist.getId(), musica.getId());
    }
}
