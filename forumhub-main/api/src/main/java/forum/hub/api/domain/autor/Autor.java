package forum.hub.api.domain.autor;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.List;

@Table(name = "tb_autores")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String perfil;

    public Autor(DetalhesAutorDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = codificarSenha(dados.senha());
        this.perfil = dados.perfil();
    }

    public String codificarSenha(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senha);
            return passwordEncoder.encode(senha);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao codificar senha", exception);
        }
    }

    private Instant calcularDataExpiracao() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}