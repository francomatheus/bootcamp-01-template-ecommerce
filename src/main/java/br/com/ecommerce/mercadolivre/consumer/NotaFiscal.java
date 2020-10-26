package br.com.ecommerce.mercadolivre.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "nota-fiscal", url = "http://localhost:8080/outro-sistema")
@Service
public interface NotaFiscal {

    @RequestMapping(method = RequestMethod.POST, path = "/notas-fiscais")
    String processa(Map<String,Long> corpoNotaFiscal);
}
