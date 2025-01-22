package forum.hub.api.domain.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositorioDeAutores extends JpaRepository<Autor, Long> {

    UserDetails buscarPorNome(String nome);

    @Modifying
    @Query("DELETE FROM Autor a WHERE a.id = :id")
    void removerPorId(Long id);
}