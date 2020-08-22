package br.com.israel.acervomusicasapi.repository;

import br.com.israel.acervomusicasapi.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Perfil findByNome(String nome);
}
