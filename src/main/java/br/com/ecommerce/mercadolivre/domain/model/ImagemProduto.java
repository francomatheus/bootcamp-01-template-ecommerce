package br.com.ecommerce.mercadolivre.domain.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class ImagemProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @URL
    private String pathImagem;
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    public ImagemProduto() {
    }

    public ImagemProduto(@NotBlank @URL String pathImagem, Produto produto) {
        this.pathImagem = pathImagem;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", pathImagem='" + pathImagem + '\'' +
                ", produto=" + produto +
                '}';
    }
}
