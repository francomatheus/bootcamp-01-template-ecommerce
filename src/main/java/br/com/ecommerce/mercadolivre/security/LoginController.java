package br.com.ecommerce.mercadolivre.security;

import br.com.ecommerce.mercadolivre.domain.request.LoginUsuarioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginUsuarioRequest loginUsuarioRequest){

        System.out.println(loginUsuarioRequest);

        return ResponseEntity.ok().build();
    }
}
