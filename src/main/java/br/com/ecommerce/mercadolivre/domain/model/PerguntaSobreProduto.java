package br.com.ecommerce.mercadolivre.domain.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 2
 */

@Entity
@Table(name = "perguntaProduto")
public class PerguntaSobreProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    private LocalDateTime instantePergunta = LocalDateTime.now();
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    // +1
    private Produto produto;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    // +1
    private Usuario usuario;

    public PerguntaSobreProduto() {
    }

    public PerguntaSobreProduto(@NotBlank String titulo, @NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getInstantePergunta() {
        return instantePergunta;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "PerguntaSobreProduto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", instantePergunta=" + instantePergunta +
                ", produto=" + produto +
                ", usuario=" + usuario +
                '}';
    }
}
