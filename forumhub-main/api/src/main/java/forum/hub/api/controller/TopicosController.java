package forum.hub.api.controller;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.resposta.DetalhesRespostaDto;
import forum.hub.api.domain.resposta.DadosNovaRespostaDto;
import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.resposta.RepositorioDeRespostas;
import forum.hub.api.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class ControladorDeTopicos {

    @Autowired
    private RepositorioDeTopicos repositorioDeTopicos;

    @Autowired
    private ServicoDeTopicos servicoDeTopicos;

    @Autowired
    private RepositorioDeRespostas repositorioDeRespostas;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(
            @RequestBody DadosNovoTopicoDto dados,
            UriComponentsBuilder uriBuilder){

        var topicoDetalhado = servicoDeTopicos.criarTopico(dados);

        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topicoDetalhado.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(topicoDetalhado);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTopico(
            @RequestBody @Valid DadosAtualizacaoTopico dados) {

        if (!repositorioDeTopicos.existsById(dados.id())) {
            throw new ValidacaoException("O ID do tópico informado não existe!");
        }

        var topico = repositorioDeTopicos.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalhesTopicoDto(topico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTopicoDto>> listarTopicos(
            @PageableDefault(page = 0, size = 5, sort = {"titulo"})
            Pageable paginacao) {

        var page = repositorioDeTopicos.findAllByStatusTrue(paginacao)
                .map(ListagemTopicoDto::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {

        if (!repositorioDeTopicos.existsById(id)) {
            throw new ValidacaoException("O ID do tópico informado não existe!");
        }

        var topico = repositorioDeTopicos.getReferenceById(id);
        topico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {

        if (!repositorioDeTopicos.existsById(id)) {
            throw new ValidacaoException("O ID do tópico informado não existe!");
        }

        var topico = repositorioDeTopicos.getReferenceById(id);

        return ResponseEntity.ok(new DetalhesTopicoDto(topico));
    }

    @GetMapping("/listar-respostas/{id}")
    public ResponseEntity<Page<DetalhesRespostaDto>> listarRespostasPorTopicos(
            @PageableDefault(page = 0, size = 5, sort = {"autor"})
            Pageable paginacao,
            @PathVariable Long id) {

        var topico = repositorioDeTopicos.getReferenceById(id);

        var page = repositorioDeRespostas.buscarRespostasPorTopico(paginacao, topico)
                .map(DetalhesRespostaDto::new);

        return ResponseEntity.ok(page);
    }
}
