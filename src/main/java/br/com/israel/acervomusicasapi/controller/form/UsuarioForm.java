package br.com.israel.acervomusicasapi.controller.form;

import br.com.israel.acervomusicasapi.models.Perfil;
import br.com.israel.acervomusicasapi.models.Usuario;
import br.com.israel.acervomusicasapi.repository.PerfilRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioForm {
    @NotEmpty
    @Size(min = 1, max = 100)
    private String nome;
    @Email
    private String email;
    @NotEmpty @Size(min = 1, max = 255)
    private String senha;
    @NotEmpty @Size(min = 1, max = 100)
    private String nomePerfil;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public Usuario converter(PerfilRepository repository) {
        Usuario usuario = new Usuario(this.nome, this.email, criptografarSenha(this.senha));
        Perfil perfil = repository.findByNome(this.nomePerfil);
        usuario.adicionaPerfil(perfil);

        return usuario;
    }

    private String criptografarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
