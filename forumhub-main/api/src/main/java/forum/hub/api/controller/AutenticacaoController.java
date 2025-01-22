package forum.hub.api.controller;

import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.autor.CredenciaisDeAcesso;
import forum.hub.api.infra.security.DadosTokenJWT;
import forum.hub.api.infra.security.ServicoDeToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ControladorDeAutenticacao {

    @Autowired
    private AuthenticationManager gerenciadorAutenticacao;

    @Autowired
    private ServicoDeToken servicoDeToken;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid CredenciaisDeAcesso credenciais) {
        var tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(
                credenciais.login(), credenciais.senha());
        var autenticacao = gerenciadorAutenticacao.authenticate(tokenDeAutenticacao);

        var tokenJWT = servicoDeToken.criarToken((Autor) autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}