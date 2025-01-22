package forum.hub.api.domain.topico;

import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.autor.AutorRepository;
import forum.hub.api.domain.autor.DadosAutorDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    private AutorRepository repositorioAutor;

    @Autowired
    private TopicoRepository repositorioTopico;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deve retornar todos os tópicos marcados como ativos")
    void verificarTopicosAtivos() {

        var topicoCriado = gerarTopico();
        var topicoBuscado = repositorioTopico.findById(topicoCriado.getId());
        assertThat(topicoCriado).isEqualTo(topicoBuscado.get());
    }

    private Autor criarAutor(
            String nomeCompleto,
            String emailContato,
            String senhaAcesso,
            String descricaoPerfil) {
        var novoAutor = new Autor(gerarDadosAutor(nomeCompleto, emailContato, senhaAcesso, descricaoPerfil));
        testEntityManager.persist(novoAutor);
        return novoAutor;
    }

    private Topico gerarTopico() {

        var autorCriado = criarAutor("maria",
                "maria@forum.hub",
                "senhaSegura",
                "desenvolvedora Java");

        var novoTopico = new Topico(
                null,
                "Tópico de Teste",
                "Conteúdo do teste",
                LocalDateTime.now(),
                true,
                autorCriado,
                Curso.Java);
        testEntityManager.persist(novoTopico);
        return novoTopico;
    }

    private DadosAutorDto gerarDadosAutor(
            String nomeCompleto,
            String emailContato,
            String senhaAcesso,
            String descricaoPerfil) {
        return new DadosAutorDto(nomeCompleto, emailContato, senhaAcesso, descricaoPerfil);
    }
}