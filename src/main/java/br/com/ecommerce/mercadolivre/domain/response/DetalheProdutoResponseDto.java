package br.com.ecommerce.mercadolivre.domain.response;

import br.com.ecommerce.mercadolivre.domain.model.DetalheProdutoOpiniao;
import br.com.ecommerce.mercadolivre.domain.model.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;

public class DetalheProdutoResponseDto {

    @NotBlank
    private String nome;
    @Positive
    private BigDecimal preco;
    @NotBlank
    private String descricao;
    @PositiveOrZero
    private Double mediaNota;
    @PositiveOrZero
    private Long totalNotas;
    private Set<String> pathImagens;
    // +1
    private Set<DetalheProdutoCaracteristica> caracteristicaProdutos;
    // +1
    private Set<DetalheProdutoOpiniao> opiniao;
    private Set<String> pergunta;

    public DetalheProdutoResponseDto() {
    }

    public DetalheProdutoResponseDto(Produto produto) {
        this.pathImagens = produto.mapeiaPathImagens();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.caracteristicaProdutos = produto.mapeiaCaracteristica();
        this.descricao = produto.getDescricao();
        this.mediaNota = produto.mediaNotas();
        this.totalNotas = produto.numeroTotalDeNotas();
        this.opiniao = produto.mapeiaOpiniao();
        this.pergunta = produto.mapeiaPerguntas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getMediaNota() {
        return mediaNota;
    }

    public void setMediaNota(Double mediaNota) {
        this.mediaNota = mediaNota;
    }

    public Long getTotalNotas() {
        return totalNotas;
    }

    public void setTotalNotas(Long totalNotas) {
        this.totalNotas = totalNotas;
    }

    public Set<String> getPathImagens() {
        return pathImagens;
    }

    public void setPathImagens(Set<String> pathImagens) {
        this.pathImagens = pathImagens;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicaProdutos() {
        return caracteristicaProdutos;
    }

    public void setCaracteristicaProdutos(Set<DetalheProdutoCaracteristica> caracteristicaProdutos) {
        this.caracteristicaProdutos = caracteristicaProdutos;
    }

    public Set<DetalheProdutoOpiniao> getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(Set<DetalheProdutoOpiniao> opiniao) {
        this.opiniao = opiniao;
    }

    public Set<String> getPergunta() {
        return pergunta;
    }

    public void setPergunta(Set<String> pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public String toString() {
        return "DetalheProdutoResponseDto{" +
                "pathImagens=" + pathImagens +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", caracteristicaProdutos=" + caracteristicaProdutos +
                ", descricao='" + descricao + '\'' +
                ", mediaNota=" + mediaNota +
                ", totalNotas=" + totalNotas +
                ", opiniao=" + opiniao +
                ", pergunta=" + pergunta +
                '}';
    }
}
