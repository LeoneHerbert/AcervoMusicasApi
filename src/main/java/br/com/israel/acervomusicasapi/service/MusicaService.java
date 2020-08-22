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
	private final GenericoDao<Musica, Integer> genericoDao;

	@Autowired
	private MusicaRepository musicaRepository;

	public MusicaService() {
		genericoDao = new GenericoDao<>(this.musicaRepository, Musica.class);
	}

	@Transactional(readOnly = true)
	public Page<Musica> buscarTodos(Pageable paginacao) {
		return musicaRepository.findAll(paginacao);
	}
	
	@Transactional
	public Musica salva(Musica musica) {
		return genericoDao.salvar(musica);
	}
	
	@Transactional
	public Musica editar(Musica musicaAtualizacao, Musica musicaNovosDados) {
		musicaAtualizacao.setNome(musicaNovosDados.getNome());
		musicaAtualizacao.setUrl(musicaNovosDados.getUrl());

		return genericoDao.salvar(musicaAtualizacao);
	}

	@Transactional(readOnly = true)
	public Optional<Musica> buscarPor(Integer id) {
		return genericoDao.buscarPor(id);
	}

	@Transactional
	public void excluir(Integer id) {
		genericoDao.excluirPor(id);
	}

	@Transactional(readOnly = true)
	public Page<Musica> buscarPorNome(String nomeMusica, Pageable paginacao) {
		return musicaRepository.findByNome(nomeMusica, paginacao);
	}
}
