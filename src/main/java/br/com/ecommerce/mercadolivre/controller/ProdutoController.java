package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.request.ProdutoRequest;
import br.com.ecommerce.mercadolivre.domain.response.ProdutoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest,
                                         UriComponentsBuilder uriComponentsBuilder,
                                         @AuthenticationPrincipal String usuarioLogado){


        Produto produto = produtoRequest.toModel(manager, usuarioLogado);

        manager.persist(produto);

        ProdutoResponseDto produtoResponseDto = new ProdutoResponseDto(produto);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/produto/{id}").buildAndExpand(produtoResponseDto.getId()).toUri())
                .build();
    }
}
