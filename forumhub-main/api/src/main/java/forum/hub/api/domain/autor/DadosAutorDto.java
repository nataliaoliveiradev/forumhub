package forum.hub.api.domain.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DetalhesAutorDto(

        @NotBlank(message = "O nome não pode estar em branco.")
        String nome,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "O formato do email é inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha,

        @NotBlank(message = "O perfil é obrigatório.")
        String perfil) {
}
