package br.com.israel.acervomusicasapi.controller;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import br.com.israel.acervomusicasapi.controller.dto.DetalhesMusicaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.israel.acervomusicasapi.controller.dto.MusicaDto;
import br.com.israel.acervomusicasapi.controller.form.MusicaForm;
import br.com.israel.acervomusicasapi.models.Musica;
import br.com.israel.acervomusicasapi.service.MusicaService;

@RestController
@RequestMapping(value = "/musica")
public class MusicaController {
	
	@Autowired
	private MusicaService musicaService;
	
	@PostMapping
    public ResponseEntity<MusicaDto> adicionar(@RequestBody @Valid MusicaForm form, UriComponentsBuilder uriBuilder) {
        Musica musica = form.converter();
        musicaService.salva(musica);

        URI uri = uriBuilder.path("/musica/{id}").buildAndExpand(musica.getId()).toUri();
        return ResponseEntity.created( uri ).body(new MusicaDto(musica));
    }

    @GetMapping
    public Page<DetalhesMusicaDto> buscarTodos(@RequestParam(required = false) String nomeMusica,
                                       @PageableDefault(sort = "nome", direction = Sort.Direction.ASC) Pageable paginacao) {

        Page<Musica> musicas;

        if(Objects.isNull(nomeMusica)) {
            musicas = musicaService.buscarTodos(paginacao);
        }else {
            musicas = musicaService.buscarPorNome(nomeMusica, paginacao);
        }

        return DetalhesMusicaDto.converter(musicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesMusicaDto> buscarPor(@PathVariable Integer id) {
        Optional<Musica> musicaOpt = musicaService.buscarPor(id);

        if (musicaOpt.isPresent()) return ResponseEntity.ok(new DetalhesMusicaDto(musicaOpt.get()));

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaDto> editar(@PathVariable Integer id, @RequestBody @Valid MusicaForm form) {
        Optional<Musica> musicaOpt = musicaService.buscarPor(id);

        if (musicaOpt.isPresent()) {
            Musica musica = musicaService.editar(musicaOpt.get(), form.converter());
            return ResponseEntity.ok(new MusicaDto(musica));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        Optional<Musica> musicaOpt = musicaService.buscarPor(id);

        if (musicaOpt.isPresent()) {
            musicaService.excluir(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
