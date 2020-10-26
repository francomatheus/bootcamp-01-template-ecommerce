package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.domain.model.OpiniaoProduto;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 3
 */

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
    // +3
    public OpiniaoProduto toModel(Produto produto, Usuario usuario){

        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
    }
}
