package br.com.ecommerce.mercadolivre.domain.response;

import br.com.ecommerce.mercadolivre.domain.model.Categoria;

public class CategoriaResponseDto {

    private Long id;
    private String nome;
    private Categoria categoria;

    public CategoriaResponseDto(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoria = categoria.getCategoriaMae();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "CategoriaResponseDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
