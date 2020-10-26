package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.annotation.ValorValido;
import br.com.ecommerce.mercadolivre.domain.enums.StatusCompra;
import br.com.ecommerce.mercadolivre.domain.enums.TipoPagamento;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 4
 */

public class CompraFinalizadaRequest {

    @ValorValido(className = Produto.class)
    private Long produtoId;
    @NotNull @Positive
    private Integer quantidade;
    @NotNull
    // +1
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
    // +2
    public Compra toModel(Produto produto, String emailUsuarioLogado, UsuarioRepository usuarioRepository) {

        // +1
        Usuario usuario = usuarioRepository.findByLogin(emailUsuarioLogado).get();


        return new Compra(this.quantidade, this.formaPagamento, StatusCompra.INICIADA, usuario, produto);
    }
}
