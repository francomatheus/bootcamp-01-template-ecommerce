package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Categoria;
import br.com.ecommerce.mercadolivre.domain.request.CategoriaRequest;
import br.com.ecommerce.mercadolivre.domain.response.CategoriaResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Carga intrinseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {

    private static Logger logger = LoggerFactory.getLogger(CategoriaController.class);

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest, UriComponentsBuilder uriComponentsBuilder){
        // +1
        Categoria categoria = categoriaRequest.toModel(manager);
        manager.persist(categoria);
        // +1
        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto(categoria);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/categoria/{id}").buildAndExpand(categoriaResponseDto.getId()).toUri())
                .build();
    }

}
