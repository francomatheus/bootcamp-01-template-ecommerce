package br.com.ecommerce.mercadolivre.security;

import br.com.ecommerce.mercadolivre.domain.request.LoginUsuarioRequest;
import br.com.ecommerce.mercadolivre.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginUsuarioRequest loginUsuarioRequest){
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginUsuarioRequest.toAuthentication();
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = jwtService.createToken(authenticate);

            return ResponseEntity.ok().body(new TokenDto("Bearer",token));
        }catch (Exception e){

            return ResponseEntity.badRequest().build();
        }
    }
}


