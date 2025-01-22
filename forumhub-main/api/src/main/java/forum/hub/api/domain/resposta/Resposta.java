package forum.hub.api.domain.resposta;

import com.fasterxml.jackson.annotation.JsonFormat;
import forum.hub.api.domain.autor.Autor;
import forum.hub.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tb_respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Topico topico;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Column(nullable = false)
    private Boolean solucao;

    public void atualizarInformacoes(DadosAtualizacaoRespostaDto dados) {

        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }
}