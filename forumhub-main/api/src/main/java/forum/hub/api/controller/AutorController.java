package forum.hub.api.controller;

import forum.hub.api.domain.ValidacaoException;
import forum.hub.api.domain.autor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/autores")
public class ControladorDeAutores {

    @Autowired
    private RepositorioDeAutores repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity registrarAutor(
            @RequestBody @Valid DetalhesAutorDto dados,
            UriComponentsBuilder uriBuilder) {
        var novoAutor = new Autor(dados);
        repositorio.save(novoAutor);

        var uri = uriBuilder
                .path("/autores/{id}")
                .buildAndExpand(novoAutor.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DetalhamentoAutorDto(novoAutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerAutor(@PathVariable Long id) {

        if (!repositorio.existsById(id)) {
            throw new ValidacaoException("O ID do autor informado não existe!");
        }

        repositorio.removerPorId(id);

        return ResponseEntity.noContent().build();
    }
}
