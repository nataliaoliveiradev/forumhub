package forum.hub.api.domain.resposta;

public record DetalhesRespostaDto(
        Long id,
        String mensagemResposta,
        Long idTopicoRelacionado,
        String mensagemTopico,
        String nomeAutor,
        Boolean resolvido) {

    public DetalhesRespostaDto(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getId(),
                resposta.getTopico().getMensagem(),
                resposta.getAutor().getNome(),
                resposta.getSolucao());
    }
}
