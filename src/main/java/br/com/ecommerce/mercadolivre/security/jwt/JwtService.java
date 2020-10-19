package br.com.ecommerce.mercadolivre.security.jwt;

import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import br.com.ecommerce.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    @Value("${jwt-secret-key}")
    private String secretKey;
    @Value("${jwt-time-expiration}")
    private String timeExpiration;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public String createToken(Authentication authenticate) {

        Usuario subjectUsuario = (Usuario) authenticate.getPrincipal();

        Date dateGenerateToken = new Date();

        Date dateTimeExpirationToken = new Date(dateGenerateToken.getTime() + Long.parseLong(timeExpiration));

        return Jwts.builder()
                .setSubject(subjectUsuario.getLogin())
                .setIssuedAt(dateGenerateToken)
                .setExpiration(dateTimeExpirationToken)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isValid(String token) {
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Usuario> getUserFromToken(String token) {
        try{
            Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String subject = body.getSubject();
            return usuarioRepository.findByLogin(subject);
        }catch (Exception e){
            e.getMessage();
            return Optional.empty();
        }
    }
}
