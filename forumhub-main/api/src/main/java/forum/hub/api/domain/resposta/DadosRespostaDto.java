package forum.hub.api.domain.resposta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosNovaRespostaDto(
        @NotBlank(message = "A mensagem não pode estar em branco.")
        String mensagem,
        @NotNull(message = "O ID do autor é obrigatório.")
        Long idAutor,
        @NotNull(message = "O ID do tópico é obrigatório.")
        Long idTopico,
        Boolean solucao) {
}