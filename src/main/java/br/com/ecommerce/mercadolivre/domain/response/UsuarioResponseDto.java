package br.com.ecommerce.mercadolivre.domain.response;

import br.com.ecommerce.mercadolivre.domain.model.Usuario;

import java.time.LocalDateTime;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

public class UsuarioResponseDto {

    private Long id;
    private String login;
    private LocalDateTime instante;

    // +1
    public UsuarioResponseDto(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.instante = usuario.getInstante();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getInstante() {
        return instante;
    }
}
