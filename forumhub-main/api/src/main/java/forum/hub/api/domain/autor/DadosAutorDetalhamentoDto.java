package forum.hub.api.domain.autor;

public record DetalhamentoAutorDto(
        Long id,
        String nomeCompleto) {

    public DetalhamentoAutorDto(Autor autor) {
        this(
                autor.getId(),
                autor.getNome()
        );
    }
}