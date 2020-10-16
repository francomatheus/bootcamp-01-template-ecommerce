package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Categoria;
import br.com.ecommerce.mercadolivre.domain.request.CategoriaRequest;
import br.com.ecommerce.mercadolivre.domain.response.CategoriaResponseDto;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/v1/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criaCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest, UriComponentsBuilder uriComponentsBuilder){

        Categoria categoria = categoriaRequest.toModel(manager);
        manager.persist(categoria);
        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto(categoria);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/categoria/{id}").buildAndExpand(categoriaResponseDto.getId()).toUri())
                .build();
    }

}
