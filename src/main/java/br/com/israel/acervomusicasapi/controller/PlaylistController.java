package br.com.israel.acervomusicasapi.controller;

import br.com.israel.acervomusicasapi.controller.dto.DetalhesPlaylistDto;
import br.com.israel.acervomusicasapi.controller.dto.PlaylistDto;
import br.com.israel.acervomusicasapi.controller.form.PlaylistForm;
import br.com.israel.acervomusicasapi.models.Playlist;
import br.com.israel.acervomusicasapi.models.PlaylistMusica;
import br.com.israel.acervomusicasapi.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistDto> criar(@RequestBody @Valid PlaylistForm form, UriComponentsBuilder uriBuilder,
                                             @RequestHeader(name="Authorization") String token) {
        Playlist playlist = form.converter();
        playlistService.salvar(playlist, token);

        URI uri = uriBuilder.path("/playlist/{id}").buildAndExpand(playlist.getId()).toUri();
        return ResponseEntity.created( uri ).body(new PlaylistDto(playlist));
    }

    @PutMapping("/{id}/adicionarMusica")
    public ResponseEntity<PlaylistDto> adicionarMusica(@PathVariable Integer id, @RequestParam(value = "idMusica") Integer idMusica) {
        Optional<Playlist> playlistOpt = playlistService.buscarPor(id);

        if (playlistOpt.isPresent()) {
            PlaylistMusica playlistMusica = playlistService.adicionarMusica(playlistOpt.get(), idMusica);
            return ResponseEntity.ok(new PlaylistDto(playlistMusica.getPlaylist()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDto> editar(@PathVariable Integer id, @RequestBody @Valid PlaylistForm form) {
        Optional<Playlist> playlistOpt = playlistService.buscarPor(id);

        if (playlistOpt.isPresent()) {
            Playlist playlist = playlistService.editar(playlistOpt.get(), form.converter());
            return ResponseEntity.ok(new PlaylistDto(playlist));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public Page<DetalhesPlaylistDto> buscarTodos(@RequestHeader(name="Authorization") String token,
                                                 @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {

        Page<Playlist> playlist = playlistService.buscarPorUsuario(token, paginacao);

        return DetalhesPlaylistDto.converter(playlist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPlaylist(@PathVariable Integer id) {
        Optional<Playlist> playlistOpt = playlistService.buscarPor(id);

        if(playlistOpt.isPresent()) {
            playlistService.excluir(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/removerMusica")
    public ResponseEntity<PlaylistDto> deletarMusicaPlaylist(@PathVariable Integer id, @RequestParam(value = "idMusica") Integer idMusica) {
        Optional<Playlist> playlistOpt = playlistService.buscarPor(id);

        if(playlistOpt.isPresent()) {
            playlistService.excluirMusica(playlistOpt.get(), idMusica);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
