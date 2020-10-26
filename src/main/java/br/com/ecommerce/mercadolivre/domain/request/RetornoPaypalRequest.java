package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.consumer.RetornoGatwayPagamento;
import br.com.ecommerce.mercadolivre.domain.enums.StatusTransacao;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.model.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 4
 */

public class RetornoPaypalRequest implements RetornoGatwayPagamento {

    @NotBlank
    private String transacaoId;
    @Min(0) @Max(1)
    private int statusCompra;

    public RetornoPaypalRequest(@NotBlank String transacaoId, @Min(0) @Max(1) int statusCompra) {

        this.transacaoId = transacaoId;
        this.statusCompra = statusCompra;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public void setTransacaoId(String transacaoId) {
        this.transacaoId = transacaoId;
    }

    public int getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(int statusCompra) {
        this.statusCompra = statusCompra;
    }

    // +2
    public Transacao toTransacao(Compra compra){
        // +2
        return new Transacao(this.statusCompra == 0 ? StatusTransacao.erro
                : StatusTransacao.sucesso, this.transacaoId, compra);
    }

    @Override
    public String toString() {
        return "PagamentoResponse{" +
                ", pagamentoId=" + transacaoId +
                ", statusCompra='" + statusCompra + '\'' +
                '}';
    }
}
