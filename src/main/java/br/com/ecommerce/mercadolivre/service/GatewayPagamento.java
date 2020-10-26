package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 1
 */


public interface GatewayPagamento {
    // +1
    ResponseEntity<String> pagamento(Compra compra, UriComponentsBuilder uriComponentsBuilder);

}
