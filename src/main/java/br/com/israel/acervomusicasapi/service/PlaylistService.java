package br.com.israel.acervomusicasapi.service;

import br.com.israel.acervomusicasapi.config.security.TokenService;
import br.com.israel.acervomusicasapi.models.Musica;
import br.com.israel.acervomusicasapi.models.Playlist;
import br.com.israel.acervomusicasapi.models.Usuario;
import br.com.israel.acervomusicasapi.repository.MusicaRepository;
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
    public Playlist adicionarMusica(Playlist playlist, Integer idMusica) {
        Musica musica = musicaRepository.getOne(idMusica);
        playlist.adicionarMusica(musica);

        return playlistRepository.save(playlist);
    }

    @Transactional(readOnly = true)
    public Page<Playlist> buscarPorUsuario(String token, Pageable paginacao) {
        Integer idUsuario = tokenService.getIdUsuario(tokenService.retornarTokenSemBearer(token));
        Usuario usuario = usuarioRepository.getOne(idUsuario);

        return playlistRepository.findAllByUsuario(usuario, paginacao);
    }
}
