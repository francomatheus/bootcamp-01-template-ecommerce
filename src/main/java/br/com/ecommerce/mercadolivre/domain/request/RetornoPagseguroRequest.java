package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.consumer.RetornoGatwayPagamento;
import br.com.ecommerce.mercadolivre.domain.enums.StatusCompraFinalizada;
import br.com.ecommerce.mercadolivre.domain.enums.StatusTransacao;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.model.Transacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatwayPagamento {

    @NotBlank
    private String transacaoId;
    @NotNull
    private StatusTransacao statusCompra;

    public RetornoPagseguroRequest(@NotBlank String transacaoId, @NotNull StatusTransacao statusCompra) {

        this.transacaoId = transacaoId;
        this.statusCompra = statusCompra;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public void setTransacaoId(String transacaoId) {
        this.transacaoId = transacaoId;
    }

    public StatusTransacao getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(StatusTransacao statusCompra) {
        this.statusCompra = statusCompra;
    }

    public Transacao toTransacao(Compra compra){
        return new Transacao(this.statusCompra, this.transacaoId, compra);
    }

    @Override
    public String toString() {
        return "PagamentoResponse{" +
                ", pagamentoId=" + transacaoId +
                ", statusCompra='" + statusCompra + '\'' +
                '}';
    }
}
