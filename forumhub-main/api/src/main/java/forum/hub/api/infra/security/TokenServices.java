package forum.hub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import forum.hub.api.domain.autor.Autor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ServicoDeToken {

    @Value("${api.security.token.secret}")
    private String segredo;

    public String criarToken(Autor autor) {
        try {
            var algoritmo = Algorithm.HMAC256(segredo);
            return JWT.create()
                    .withIssuer("API ForumHub")
                    .withSubject(autor.getNome())
                    .withExpiresAt(expiracaoToken())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao criar o token JWT", exception);
        }
    }

    public String obterSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(segredo);
            return JWT.require(algoritmo)
                    .withIssuer("API ForumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant expiracaoToken() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}