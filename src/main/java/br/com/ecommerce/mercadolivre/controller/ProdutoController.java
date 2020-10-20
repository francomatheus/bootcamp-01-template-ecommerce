package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.request.ProdutoImagemRequest;
import br.com.ecommerce.mercadolivre.domain.request.ProdutoRequest;
import br.com.ecommerce.mercadolivre.domain.response.ProdutoResponseDto;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import br.com.ecommerce.mercadolivre.storage.StorageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StorageFile storageFile;

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest,
                                         UriComponentsBuilder uriComponentsBuilder,
                                         @AuthenticationPrincipal String usuarioLogado){

        // +1
        Produto produto = produtoRequest.toModel(manager, usuarioRepository, usuarioLogado);

        manager.persist(produto);
        // +1
        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(produto);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/produto/{id}").buildAndExpand(produtoResponseDto.getId()).toUri())
                .build();
    }

    @PostMapping("/{id}/imagem")
    @Transactional
    public ResponseEntity<?> adicionaImagemProduto(@PathVariable Long id,
                                                   @Valid ProdutoImagemRequest imagemProduto,
                                                   UriComponentsBuilder uriComponentsBuilder,
                                                   @AuthenticationPrincipal String usuarioLogado){

        Produto produto = manager.find(Produto.class, id);
        if(produto.donoDoProduto()==usuarioLogado){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não pode alterar produto, pois não é dono !!!");
        }

        List<String> pathImagens = storageFile.salvaImagem(imagemProduto);

        produto.adicionaImagemProduto(pathImagens);

        manager.merge(produto);

        return ResponseEntity
                .ok()
                .build();
    }
}
