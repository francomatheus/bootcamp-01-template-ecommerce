package br.com.ecommerce.mercadolivre.domain.model;

import javax.validation.constraints.NotBlank;

public class DetalheProdutoOpiniao {

    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;

    public DetalheProdutoOpiniao(OpiniaoProduto opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
