package br.com.israel.acervomusicasapi.service;

import br.com.israel.acervomusicasapi.models.Usuario;
import br.com.israel.acervomusicasapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salva(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
