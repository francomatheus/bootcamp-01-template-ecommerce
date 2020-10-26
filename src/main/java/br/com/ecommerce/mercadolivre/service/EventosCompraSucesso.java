package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 1
 */


public interface EventosCompraSucesso {
    // +1
    void processa(Compra compra);

}
