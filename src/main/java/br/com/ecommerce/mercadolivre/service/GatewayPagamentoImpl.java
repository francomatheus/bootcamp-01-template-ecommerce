package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.consumer.PagSeguroPagamento;
import br.com.ecommerce.mercadolivre.consumer.PaypalPagamento;
import br.com.ecommerce.mercadolivre.domain.enums.TipoPagamento;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class GatewayPagamentoImpl implements GatewayPagamento{

    private static Logger logger = LoggerFactory.getLogger(GatewayPagamentoImpl.class);

    @Autowired
    // +1
    private PagSeguroPagamento pagSeguroPagamento;

    @Autowired
    // +1
    private PaypalPagamento paypalPagamento;



    // +1
    public ResponseEntity<String> pagamento(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
        logger.info("Iniciando pagamento");
        URI retornoAppPosPagamento = uriComponentsBuilder.path("/v1/retorno-pagamento/{id}").buildAndExpand(compra.getId()).toUri();

        Map<String,String> body = new HashMap<>();
        body.put("compraId", compra.getId().toString());
        body.put("quantidade", compra.getQuantidade().toString());
        body.put("formaPagamento", compra.getFormaPagamento().toString());
        body.put("statusCompra", compra.getStatusCompra().toString());

        // +1
        if (TipoPagamento.valueOf("PAGSEGURO").equals(compra.getFormaPagamento())){
            logger.info("Pagamento PagSeguro");
            //RetornoPagseguroRequest pagamentoRequest = pagSeguroPagamento.compraPagamento(body);
            System.out.println("Pagamento PagSeguro");
            //System.out.println(pagamentoRequest);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(String.format("pagseguro.com/%s?redirectUrl=%s",compra.getId(),retornoAppPosPagamento));
        }
        // +1
        else if (TipoPagamento.valueOf("PAYPAL").equals(compra.getFormaPagamento())){
            logger.info("Pagamento Paypal");
            //RetornoPagseguroRequest pagamentoRequest = paypalPagamento.compraPagamento(body);

            //System.out.println(pagamentoRequest);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(String.format("paypal.com/%s?redirectUrl=%s",compra.getId(),retornoAppPosPagamento));
        }
        return ResponseEntity.badRequest().body("Tipo de pagamento não encontrado!!");
    }
}
