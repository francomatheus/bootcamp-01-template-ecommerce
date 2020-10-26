package br.com.ecommerce.mercadolivre.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "ranking", url = "http://localhost:8080/outro-sistema")
public interface Ranking {

    @RequestMapping(method = RequestMethod.POST, path = "/ranking")
    String processa(Map<String,Long> corpoRanking);
}
