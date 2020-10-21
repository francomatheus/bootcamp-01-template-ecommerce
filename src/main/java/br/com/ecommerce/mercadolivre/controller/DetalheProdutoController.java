package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.response.DetalheProdutoResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Carga intrinseca máxima permitida - 7
 * Carga intrínseca da classe - 2
 */

@RestController
@RequestMapping("/v1/produtos")
public class DetalheProdutoController {

    private static Logger logger = LoggerFactory.getLogger(DetalheProdutoController.class);

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalheProduto(@PathVariable Long id){

        logger.info("Recebendo requisição com o valor do id: {}", id);
        Produto produto = manager.find(Produto.class, id);
        // +1
        Assert.isTrue(produto != null, "Produto não encontrado para id informado!!");
        // +1
        DetalheProdutoResponseDto detalheProdutoResponseDto = new DetalheProdutoResponseDto(produto);
        logger.info("Dados de detalhe do produto coletados: {}", detalheProdutoResponseDto);
        return ResponseEntity.ok(detalheProdutoResponseDto);
    }
}
