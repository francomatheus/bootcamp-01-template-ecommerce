package br.com.ecommerce.mercadolivre.domain.request;

import br.com.ecommerce.mercadolivre.annotation.ValorUnico;
import br.com.ecommerce.mercadolivre.annotation.ValorValido;
import br.com.ecommerce.mercadolivre.domain.model.Categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequest {

    @NotBlank
    @ValorUnico(className = Categoria.class, fieldName = "nome")
    private String nome;
    @Positive
    @ValorValido(className = Categoria.class)
    private Long categoriaMaeId;

    public CategoriaRequest(@NotBlank String nome, @Positive Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(EntityManager manager){
        Categoria categoria = new Categoria(this.nome);
        if (this.categoriaMaeId != null){
            Categoria categoriaMae = manager.find(Categoria.class, this.categoriaMaeId);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }

    @Override
    public String toString() {
        return "CategoriaRequest{" +
                "nome='" + nome + '\'' +
                ", categoriaMaeId=" + categoriaMaeId +
                '}';
    }
}
