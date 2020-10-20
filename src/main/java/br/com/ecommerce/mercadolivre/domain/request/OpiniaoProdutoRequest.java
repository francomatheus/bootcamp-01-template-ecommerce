package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.domain.model.OpiniaoProduto;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoProdutoRequest {

    @Min(1) @Max(5)
    @NotNull
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Length(max = 500)
    private String descricao;

    public OpiniaoProdutoRequest(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank @Length(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return "OpiniaoProdutoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public OpiniaoProduto toModel(Produto produto, Usuario usuario){

        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
    }
}
