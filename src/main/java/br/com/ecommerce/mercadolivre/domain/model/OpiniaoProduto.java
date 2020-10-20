package br.com.ecommerce.mercadolivre.domain.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "opiniaoProduto")
public class OpiniaoProduto {

    @Id @GeneratedValue
    private Long id;

    @Min(1) @Max(5)
    @NotNull
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioByLogin;
    @NotNull @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    public OpiniaoProduto() {
    }

    public OpiniaoProduto(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao, @NotNull @Valid Usuario usuarioByLogin, @NotNull @Valid Produto produto) {

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioByLogin = usuarioByLogin;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuarioByLogin() {
        return usuarioByLogin;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "OpiniaoProduto{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuarioByLogin=" + usuarioByLogin +
                ", produto=" + produto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpiniaoProduto that = (OpiniaoProduto) o;
        return Objects.equals(usuarioByLogin, that.usuarioByLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioByLogin);
    }
}
