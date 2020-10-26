package br.com.ecommerce.mercadolivre.domain.outroSistema.request;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private Long compraId;
    @NotNull
    private Long vendedorId;

    public RankingRequest(@NotNull Long compraId, @NotNull Long vendedorId) {
        this.compraId = compraId;
        this.vendedorId = vendedorId;
    }

    public Long getCompraId() {
        return compraId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "compraId=" + compraId +
                ", vendedorId=" + vendedorId +
                '}';
    }
}
