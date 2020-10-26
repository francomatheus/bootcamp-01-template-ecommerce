package br.com.ecommerce.mercadolivre.controller.outroSistema;

import br.com.ecommerce.mercadolivre.domain.outroSistema.request.NotaFiscalRequest;
import br.com.ecommerce.mercadolivre.domain.outroSistema.request.RankingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/outro-sistema")
public class OutroSistemaController {

    private static Logger logger = LoggerFactory.getLogger(OutroSistemaController.class);

    @PostMapping("/notas-fiscais")
    public ResponseEntity<?> criaNotaFiscal(@Valid @RequestBody NotaFiscalRequest notaFiscalRequest){

        logger.info("Valores Compra: {}",notaFiscalRequest.toString());


        return ResponseEntity.ok().body("Testando nota Fiscal");
    }

    @PostMapping("/ranking")
    public ResponseEntity<?> ranking(@Valid @RequestBody RankingRequest rankingRequest){

        logger.info("Valores Compra: {} ",rankingRequest.toString());



        return ResponseEntity.ok().body("Rankin recebido");
    }
}
