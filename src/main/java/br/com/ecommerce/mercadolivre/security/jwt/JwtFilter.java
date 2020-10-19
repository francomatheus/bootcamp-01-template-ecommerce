package br.com.ecommerce.mercadolivre.security.jwt;

import br.com.ecommerce.mercadolivre.domain.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> tokenFromHeader = getTokenFromHeader(request);

        if(tokenFromHeader.isPresent() && jwtService.isValid(tokenFromHeader.get())){

            Optional<Usuario> userFromToken = jwtService.getUserFromToken(tokenFromHeader.get());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = userFromToken.get().toAuthentication();

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenFromHeader(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ") || token == ""){
            return Optional.empty();
        }
        return Optional.of(token.substring(7, token.length()));
    }
}
