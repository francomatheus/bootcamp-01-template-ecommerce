package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.domain.model.PerguntaSobreProduto;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;

import javax.validation.constraints.NotBlank;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 3
 */

public class PerguntaSobreProdutoRequest {

    @NotBlank
    private String titulo;

    public PerguntaSobreProdutoRequest() {
    }

    public PerguntaSobreProdutoRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "PerguntaSobreProdutoRequest{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
    // +3
    public PerguntaSobreProduto toModel(Produto produto, Usuario usuario) {
        return new PerguntaSobreProduto(this.titulo, produto, usuario);
    }
}
