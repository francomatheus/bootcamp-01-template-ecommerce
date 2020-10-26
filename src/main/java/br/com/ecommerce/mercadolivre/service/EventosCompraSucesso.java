package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;

public interface EventosCompraSucesso {

    void processa(Compra compra);

}
