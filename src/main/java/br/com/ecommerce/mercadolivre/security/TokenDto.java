package br.com.ecommerce.mercadolivre.security;

import javax.validation.constraints.NotBlank;

public class TokenDto {
    @NotBlank
    private String tipo;
    private String token;

    public TokenDto(String tipo, String token) {
        this.tipo = tipo;
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }
}
