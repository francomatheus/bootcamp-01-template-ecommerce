package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.annotation.ValorValido;
import br.com.ecommerce.mercadolivre.domain.model.OpiniaoProduto;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.domain.request.OpiniaoProdutoRequest;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/produtos")
public class OpiniaoProdutoController {

    private static Logger logger = LoggerFactory.getLogger(OpiniaoProdutoController.class);

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    // +1
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/opiniao")
    @Transactional
    // +1 OpiniaoProdutoRequest
    public ResponseEntity<?> criaOpiniaoSobreProduto(@PathVariable Long id,
                                                     @RequestBody @Valid OpiniaoProdutoRequest opiniaoProdutoRequest,
                                                     @AuthenticationPrincipal String emailUsuarioLogado,
                                                     UriComponentsBuilder uriComponentsBuilder){

        // +1
        Produto produto = manager.find(Produto.class, id);
        // +1
        Assert.isTrue(produto!=null, "Produto não encontrado no banco de dados!!");
        // +1
        Usuario usuario = usuarioRepository.findByLogin(emailUsuarioLogado).get();
        // +1
        OpiniaoProduto opiniaoProduto = opiniaoProdutoRequest.toModel(produto, usuario);

        produto.adicionaOpiniaoProduto(opiniaoProduto);

        manager.merge(produto);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/produto/{id}/opiniao/{idOpiniao}").buildAndExpand(id,opiniaoProduto.getId()).toUri())
                .build();
    }
}
