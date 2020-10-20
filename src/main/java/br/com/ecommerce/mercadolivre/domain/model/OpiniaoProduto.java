package br.com.ecommerce.mercadolivre.domain.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull @ManyToOne
    private Usuario usuarioByLogin;
    @NotNull @ManyToOne
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
}
