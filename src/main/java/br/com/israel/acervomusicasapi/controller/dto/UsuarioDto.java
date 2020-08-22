package br.com.israel.acervomusicasapi.controller.dto;

import br.com.israel.acervomusicasapi.models.Usuario;

public class UsuarioDto {
    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
