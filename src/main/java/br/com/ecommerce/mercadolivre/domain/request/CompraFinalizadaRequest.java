package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.annotation.ValorValido;
import br.com.ecommerce.mercadolivre.domain.enums.StatusCompra;
import br.com.ecommerce.mercadolivre.domain.enums.TipoPagamento;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraFinalizadaRequest {

    @ValorValido(className = Produto.class)
    private Long produtoId;
    @NotNull @Positive
    private Integer quantidade;
    @NotNull
    private TipoPagamento formaPagamento;

    public CompraFinalizadaRequest(Long produtoId, @NotNull @Positive Integer quantidade, @NotBlank TipoPagamento formaPagamento) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public TipoPagamento getFormaPagamento() {
        return formaPagamento;
    }

    @Override
    public String toString() {
        return "CompraFinalizadaRequest{" +
                "produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                ", formaPagamento='" + formaPagamento + '\'' +
                '}';
    }

    public Compra toModel(Produto produto, Usuario usuario) {
        return new Compra(this.quantidade, this.formaPagamento, StatusCompra.INICIADA, usuario, produto);
    }
}
