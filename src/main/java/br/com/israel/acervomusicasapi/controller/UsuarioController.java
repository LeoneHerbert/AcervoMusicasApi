package br.com.israel.acervomusicasapi.controller;

import br.com.israel.acervomusicasapi.controller.dto.UsuarioDto;
import br.com.israel.acervomusicasapi.controller.form.UsuarioForm;
import br.com.israel.acervomusicasapi.models.Usuario;
import br.com.israel.acervomusicasapi.repository.PerfilRepository;
import br.com.israel.acervomusicasapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter(perfilRepository);
        usuarioService.salva(usuario);

        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created( uri ).body(new UsuarioDto(usuario));
    }
}
