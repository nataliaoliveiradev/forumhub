package forum.hub.api.domain.autor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {

    @Autowired
    private AutorRepository repositorioAutor;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deve realizar o cadastro de um autor corretamente")
    void verificarCadastroAutor() {

        var novoAutor = criarAutor("maria",
                "maria@forum.hub",
                "senhaSegura",
                "desenvolvedora Java");

        var autorRetornado = repositorioAutor.findById(novoAutor.getId());
        assertThat(novoAutor).isEqualTo(autorRetornado.get());
    }

    private Autor criarAutor(
            String nomeCompleto,
            String emailContato,
            String senhaAcesso,
            String descricaoPerfil) {
        var autorCriado = new Autor(gerarDadosAutor(nomeCompleto, emailContato, senhaAcesso, descricaoPerfil));
        testEntityManager.persist(autorCriado);
        return autorCriado;
    }

    private DadosAutorDto gerarDadosAutor(
            String nomeCompleto,
            String emailContato,
            String senhaAcesso,
            String descricaoPerfil) {
        return new DadosAutorDto(nomeCompleto, emailContato, senhaAcesso, descricaoPerfil);
    }
}
