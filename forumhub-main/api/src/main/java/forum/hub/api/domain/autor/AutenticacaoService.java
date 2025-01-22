package forum.hub.api.domain.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicoDeAutenticacao implements UserDetailsService {

    @Autowired
    private RepositorioDeAutores repositorio;

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        var usuario = repositorio.buscarPorNome(nomeUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + nomeUsuario);
        }
        return usuario;
    }
}
