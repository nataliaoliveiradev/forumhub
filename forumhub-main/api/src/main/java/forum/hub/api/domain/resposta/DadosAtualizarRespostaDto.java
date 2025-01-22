package forum.hub.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoRespostaDto(
        @NotNull(message = "O ID da resposta é obrigatório.")
        Long id,
        @NotBlank(message = "A mensagem não pode estar em branco.")
        String novaMensagem) {
}