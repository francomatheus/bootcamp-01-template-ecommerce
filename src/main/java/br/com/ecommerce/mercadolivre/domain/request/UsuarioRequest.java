package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.annotation.ValorUnico;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 1
 */

public class UsuarioRequest {

    @NotBlank
    @Email
    @ValorUnico(className = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;


    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }
    // +1
    public Usuario toModel(){
        return new Usuario(this.login, this.senha);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "UsuarioRequest{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
