package forum.hub.api.infra.exception;

import forum.hub.api.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GerenciadorDeExcecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErroNaoEncontrado() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroArgumentoInvalido(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(
                erros.stream().map(DetalhesErroValidacao::new).toList()
        );
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroDeValidacao(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DetalhesErroValidacao (String campo, String mensagem) {
        public DetalhesErroValidacao(FieldError erro) {
            this(
                    erro.getField(),
                    erro.getDefaultMessage()
            );
        }
    }
}
