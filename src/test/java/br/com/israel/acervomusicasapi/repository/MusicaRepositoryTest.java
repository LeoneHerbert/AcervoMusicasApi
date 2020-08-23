package br.com.israel.acervomusicasapi.repository;

import br.com.israel.acervomusicasapi.builder.MusicaBuilder;
import br.com.israel.acervomusicasapi.models.Musica;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MusicaRepositoryTest {
    @Autowired
    private MusicaRepository musicaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Musica musica;

    @Before
    public void start() {
        musica = MusicaBuilder.umaMusica().constroi();
    }

    @Test
    public void deveRetornarExceptionSalvandoComNomeNulo() {
        expectedException.expect(DataIntegrityViolationException.class);

        Musica musicateste = new Musica();
        musicateste.setUrl("http://www.google.com/");

        musicaRepository.save(musicateste);
    }

    @Test
    public void deveRetornarExceptionSalvandoComUrlNula() {
        expectedException.expect(DataIntegrityViolationException.class);

        Musica musicateste = new Musica();
        musicateste.setNome("Nome Teste");

        musicaRepository.save(musicateste);
    }

    @Test
    public void deveSalvarMusica() {
        Musica save = musicaRepository.save(musica);
        Musica musicasave = musicaRepository.getOne(save.getId());

        Assert.assertNotNull(musicasave);
        Assert.assertEquals(save.getId(), musicasave.getId());

        musicaRepository.delete(musicasave);
    }

    @Test
    public void deleteByIdDeveRemoverRegistroMusica() {
        Musica save = musicaRepository.save(musica);
        Musica musicasave = musicaRepository.getOne(save.getId());

        Assert.assertNotNull(musicasave);
        Assert.assertEquals(save.getId(), musicasave.getId());

        musicaRepository.deleteById(musicasave.getId());
        Optional<Musica> result = musicaRepository.findById(musicasave.getId());

        assertThat(false, is(equalTo(result.isPresent())));
    }
}
