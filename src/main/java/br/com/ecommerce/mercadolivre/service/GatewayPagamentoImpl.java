package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.enums.TipoPagamento;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GatewayPagamentoImpl implements GatewayPagamento{

    // +1
    public ResponseEntity<String> pagamento(Compra compra, UriComponentsBuilder uriComponentsBuilder) {

        URI retornoAppPosPagamento = uriComponentsBuilder.path("/produto/{id}").buildAndExpand(compra.getProdutoId()).toUri();

        // +1
        if (TipoPagamento.valueOf("PAGSEGURO").equals(compra.getFormaPagamento())){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(String.format("pagseguro.com/%s?redirectUrl=%s",compra.getId(),retornoAppPosPagamento));
        }
        // +1
        else if (TipoPagamento.valueOf("PAYPAL").equals(compra.getFormaPagamento())){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(String.format("paypal.com/%s?redirectUrl=%s",compra.getId(),retornoAppPosPagamento));
        }
        return ResponseEntity.badRequest().build();
    }
}
