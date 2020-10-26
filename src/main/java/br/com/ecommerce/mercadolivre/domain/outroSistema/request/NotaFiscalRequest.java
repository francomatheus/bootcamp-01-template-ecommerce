package br.com.ecommerce.mercadolivre.domain.outroSistema.request;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long compraId;
    @NotNull
    private Long compradorId;

    public NotaFiscalRequest(@NotNull Long compraId, @NotNull Long compradorId) {
        this.compraId = compraId;
        this.compradorId = compradorId;
    }

    public Long getCompraId() {
        return compraId;
    }

    public Long getCompradorId() {
        return compradorId;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "compraId=" + compraId +
                ", compradorId=" + compradorId +
                '}';
    }
}
