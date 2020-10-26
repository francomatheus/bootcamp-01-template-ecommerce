package br.com.ecommerce.mercadolivre.consumer;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.model.Transacao;

public interface RetornoGatwayPagamento {

    Transacao toTransacao(Compra compra);
}
