package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.domain.request.UsuarioRequest;
import br.com.ecommerce.mercadolivre.domain.response.UsuarioResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Carga intrinseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    // +1
    public ResponseEntity<?> criaUsuario(@RequestBody @Validated UsuarioRequest usuarioRequest, UriComponentsBuilder uriComponentsBuilder){
        // +1
        Usuario usuario = usuarioRequest.toModel();

        manager.persist(usuario);
        // +1
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto(usuario);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/usuario/{id}").buildAndExpand(usuarioResponseDto.getId()).toUri())
                .build();
    }
}
