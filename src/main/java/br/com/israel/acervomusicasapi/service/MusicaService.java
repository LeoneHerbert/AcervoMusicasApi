package br.com.israel.acervomusicasapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.israel.acervomusicasapi.models.Musica;
import br.com.israel.acervomusicasapi.repository.MusicaRepository;

import java.util.Optional;

@Service
public class MusicaService {
	@Autowired
	private MusicaRepository musicaRepository;

	@Transactional(readOnly = true)
	public Page<Musica> buscarTodos(Pageable paginacao) {
		return musicaRepository.findAll(paginacao);
	}
	
	@Transactional
	public Musica salva(Musica musica) {
		return musicaRepository.save(musica);
	}
	
	@Transactional
	public Musica editar(Musica musicaAtualizacao, Musica musicaNovosDados) {
		musicaAtualizacao.setNome(musicaNovosDados.getNome());
		musicaAtualizacao.setUrl(musicaNovosDados.getUrl());

		return musicaRepository.save(musicaAtualizacao);
	}

	@Transactional(readOnly = true)
	public Optional<Musica> buscarPor(Integer id) {
		return musicaRepository.findById(id);
	}

	@Transactional
	public void excluir(Integer id) {
		musicaRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Page<Musica> buscarPorNome(String nomeMusica, Pageable paginacao) {
		return musicaRepository.findByNome(nomeMusica, paginacao);
	}
}
