package br.com.ecommerce.mercadolivre.domain.response;

import br.com.ecommerce.mercadolivre.domain.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioResponseDto {

    private Long id;
    private String login;
    private LocalDateTime instante;

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
