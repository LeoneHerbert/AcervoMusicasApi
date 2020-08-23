package br.com.israel.acervomusicasapi.service;

import br.com.israel.acervomusicasapi.builder.MusicaBuilder;
import br.com.israel.acervomusicasapi.models.Musica;
import br.com.israel.acervomusicasapi.repository.MusicaRepository;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicaServiceTest {

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private MusicaRepository musicaRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Musica musica;

    @Before
    public void start() {
        this.musica = MusicaBuilder.umaMusica().constroi();
    }

    @Test
    public void inserirMusicaComNomeVazioDeveLancarException() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("A música deve conter um nome e uma url.");

        Musica musicaTest = MusicaBuilder.umaMusica().semNome().constroi();
        musicaService.salva(musicaTest);
    }

    @Test
    public void inserirMusicaComUrlVaziaDeveLancarException() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("A música deve conter um nome e uma url.");

        Musica musicaTest = MusicaBuilder.umaMusica().comUrlVazia().constroi();
        musicaService.salva(musicaTest);
    }

    @Test
    public void deveSalvarMusica() {
        Musica musicalSalva = musicaService.salva(this.musica);
        Optional<Musica> musicaBD = musicaRepository.findById(musicalSalva.getId());

        Assert.assertNotNull(musicaBD.get());
        Assert.assertEquals(musicalSalva.getId(), musicaBD.get().getId());

        musicaRepository.delete(musicalSalva);
    }

    @Test
    public void deveEditarMusica() {
        Musica musicaSalva = musicaService.salva(this.musica);
        Musica musicaNovosDados = MusicaBuilder.umaMusica().comNome("Teste Editar").constroi();

        musicaSalva = musicaService.editar(musicaSalva, musicaNovosDados);
        Musica musicaEditada = musicaService.buscarPor(musicaSalva.getId()).get();

        Assert.assertNotNull(musicaEditada);
        Assert.assertEquals(musicaEditada.getNome(), musicaSalva.getNome());
        assertThat(musicaEditada, is(equalTo(musicaSalva)));

        musicaRepository.delete(musicaSalva);
    }

    @Test
    public void editarMusicaComUrlVaziaDeveLancarException() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("A música deve conter um nome e uma url.");

        Musica musicaSalva = musicaService.salva(this.musica);
        Musica musicaNovosDados = MusicaBuilder.umaMusica().comUrlVazia().constroi();

        musicaService.editar(musicaSalva, musicaNovosDados);

        musicaRepository.delete(musicaSalva);
    }

    @Test
    public void editarMusicaCoNomeVazioDeveLancarException() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("A música deve conter um nome e uma url.");

        Musica musicaSalva = musicaService.salva(this.musica);
        Musica musicaNovosDados = MusicaBuilder.umaMusica().semNome().constroi();

        musicaService.editar(musicaSalva, musicaNovosDados);

        musicaRepository.delete(musicaSalva);
    }
}
