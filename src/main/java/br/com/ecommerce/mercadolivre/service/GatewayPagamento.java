package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface GatewayPagamento {

    ResponseEntity<String> pagamento(Compra compra, UriComponentsBuilder uriComponentsBuilder);

}
