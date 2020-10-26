package br.com.ecommerce.mercadolivre.consumer;

import br.com.ecommerce.mercadolivre.domain.request.RetornoPagseguroRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "paypal",url = "http://localhost:8081")
public interface PaypalPagamento {

    @RequestMapping(method = RequestMethod.POST, path = "/paypal",consumes = "application/json")
    RetornoPagseguroRequest compraPagamento(Map<String, String> compra);
}
