package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.request.ProdutoImagemRequest;
import br.com.ecommerce.mercadolivre.domain.request.ProdutoRequest;
import br.com.ecommerce.mercadolivre.domain.response.ProdutoResponseDto;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import br.com.ecommerce.mercadolivre.storage.StorageFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

/**
 * Carga intrinseca máxima permitida - 7
 * Carga intrínseca da classe - 8
 */

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private static Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    // +1
    private UsuarioRepository usuarioRepository;

    @Autowired
    // +1
    private StorageFile storageFile;

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest,
                                         UriComponentsBuilder uriComponentsBuilder,
                                         @AuthenticationPrincipal String emailUsuarioLogado){

        logger.info("Recebendo requisição para criar produto: {}",produtoRequest);
        // +1
        Produto produto = produtoRequest.toModel(manager, usuarioRepository, emailUsuarioLogado);

        manager.persist(produto);
        // +1
        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(produto);
        logger.info("Produto criado: {}", produto);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/produto/{id}").buildAndExpand(produtoResponseDto.getId()).toUri())
                .build();
    }

    @PostMapping("/{id}/imagem")
    @Transactional
    // +1
    public ResponseEntity<?> adicionaImagemProduto(@PathVariable Long id,
                                                   @Valid ProdutoImagemRequest imagemProduto,
                                                   UriComponentsBuilder uriComponentsBuilder,
                                                   @AuthenticationPrincipal String emailUsuarioLogado){
        logger.info("Recebendo requisição para adicionar imagem ao produto de id: {}", id);
        Produto produto = manager.find(Produto.class, id);
        // +1
        Assert.isTrue(produto!=null, "Produto não encontrado no banco de dados!!");
        // +1
        if(!emailUsuarioLogado.equals(produto.donoDoProduto())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não pode alterar produto, pois não é dono !!!");
        }

        List<String> pathImagens = storageFile.salvaImagem(imagemProduto);

        produto.adicionaImagemProduto(pathImagens);

        manager.merge(produto);
        logger.info("imagem adicionada, com path: {} ao produto de id: {}", pathImagens, id);
        return ResponseEntity
                .ok()
                .build();
    }
}
