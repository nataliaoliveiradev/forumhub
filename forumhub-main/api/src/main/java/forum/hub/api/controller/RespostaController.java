package forum.hub.api.controller;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.resposta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
public class ControladorDeRespostas {

    @Autowired
    private RepositorioDeRespostas repositorio;

    @Autowired
    private ServicoDeResposta servicoDeResposta;

    @PostMapping
    @Transactional
    public ResponseEntity criarResposta(
            @RequestBody DadosNovaRespostaDto dados,
            UriComponentsBuilder uriBuilder) {

        var respostaDetalhada = servicoDeResposta.criarRespostaParaTopico(dados);

        var uri = uriBuilder
                .path("/respostas/{id}")
                .buildAndExpand(respostaDetalhada.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(respostaDetalhada);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarResposta(
            @RequestBody @Valid DadosAtualizacaoRespostaDto dados) {

        if (!repositorio.existsById(dados.id())) {
            throw new ValidacaoException("O ID da resposta informado não existe!");
        }

        var resposta = repositorio.getReferenceById(dados.id());
        resposta.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalhesRespostaDto(resposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirResposta(@PathVariable Long id) {

        if (!repositorio.existsById(id)) {
            throw new ValidacaoException("O ID da resposta informado não existe!");
        }

        repositorio.removerPorId(id);

        return ResponseEntity.noContent().build();
    }
}
