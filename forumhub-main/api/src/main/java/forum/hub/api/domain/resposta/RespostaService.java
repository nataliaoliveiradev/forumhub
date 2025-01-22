package forum.hub.api.domain.resposta;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.autor.AutorRepository;
import forum.hub.api.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServicoDeResposta {

    @Autowired
    private TopicoRepository repositorioDeTopicos;

    @Autowired
    private RespostaRepository repositorioDeRespostas;

    @Autowired
    private AutorRepository repositorioDeAutores;

    public DetalhesRespostaDto criarRespostaParaTopico(DadosNovaRespostaDto dados) {

        if (dados.idTopico() == null) {
            throw new ValidacaoException("O ID do tópico é obrigatório");
        }
        if (!repositorioDeTopicos.existsById(dados.idTopico())) {
            throw new ValidacaoException("O ID do tópico informado não existe!");
        }

        var topico = repositorioDeTopicos.getReferenceById(dados.idTopico());
        var autor = repositorioDeAutores.getReferenceById(topico.getAutor().getId());

        var novaResposta = new Resposta(
                null,
                dados.mensagem(),
                topico,
                LocalDateTime.now(),
                autor,
                false
        );

        repositorioDeRespostas.save(novaResposta);

        return new DetalhesRespostaDto(
                novaResposta.getId(),
                novaResposta.getMensagem(),
                topico.getId(),
                topico.getMensagem(),
                autor.getNome(),
                novaResposta.getSolucao());
    }
}
