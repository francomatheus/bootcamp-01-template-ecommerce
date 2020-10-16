package br.com.ecommerce.mercadolivre.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUsuarioRequest {

    @NotBlank
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public LoginUsuarioRequest(@NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "LoginUsuarioRequest{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
