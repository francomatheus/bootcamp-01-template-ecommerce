package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.domain.request.CompraFinalizadaRequest;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import br.com.ecommerce.mercadolivre.service.GatewayPagamento;
import br.com.ecommerce.mercadolivre.service.SendMail;
import br.com.ecommerce.mercadolivre.validators.VerificaEstoqueValidator;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/compra")
public class FinalizarCompraController {

    private static Logger logger = LoggerFactory.getLogger(FinalizarCompraController.class);

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    // +1
    private VerificaEstoqueValidator verificaEstoqueValidator;

    @Autowired
    // +1
    private UsuarioRepository usuarioRepository;

    @Autowired
    // +1
    private GatewayPagamento gatewayPagamento;

    @Autowired
    // +1
    private SendMail sendMail;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(verificaEstoqueValidator);
    }

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> finalizarCompra(@RequestBody @Valid CompraFinalizadaRequest compraFinalizadaRequest,
                                             @AuthenticationPrincipal String emailUsuarioLogado,
                                             UriComponentsBuilder uriComponentsBuilder){
        logger.info("Recebendo requisição para finalizar compra: {}", compraFinalizadaRequest);
        // +1
        Produto produto = manager.find(Produto.class, compraFinalizadaRequest.getProdutoId());
        // +1
        Assert.isTrue(produto != null, "Produto não encontrado !!");
        boolean abateEstoqueValido = produto.abateEstoque(compraFinalizadaRequest.getQuantidade());
        // +1
        Usuario usuario = usuarioRepository.findByLogin(emailUsuarioLogado).get();
        // +1
        Compra compra = compraFinalizadaRequest.toModel(produto, usuario);
        manager.persist(compra);
        sendMail.send(emailUsuarioLogado, produto.donoDoProduto(), String.format("Desejo comprar o produto: %s", produto.getNome()));

        // +1
        if (!abateEstoqueValido){
            return ResponseEntity.noContent().build();
        }

        ResponseEntity<String> finalizandoCompraPagamento = gatewayPagamento.pagamento(compra, uriComponentsBuilder);

        return finalizandoCompraPagamento;
    }
}
