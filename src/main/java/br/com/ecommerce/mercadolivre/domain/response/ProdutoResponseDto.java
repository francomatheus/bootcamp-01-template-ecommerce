package br.com.ecommerce.mercadolivre.domain.response;

import br.com.ecommerce.mercadolivre.domain.model.CaracteristicaProduto;
import br.com.ecommerce.mercadolivre.domain.model.Categoria;
import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProdutoResponseDto {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidadeDisponivel;
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    private String descricao;
    private Set<CaracteristicaProduto> caracteristicaProduto;
    private Categoria categoria;
    private Usuario usuario;

    public ProdutoResponseDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
        this.instanteCadastro = produto.getInstanteCadastro();
        this.descricao = produto.getDescricao();
        this.caracteristicaProduto = produto.getCaracteristicaProduto();
        this.categoria = produto.getCategoria();
        this.usuario = produto.getUsuario();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaProduto> getCaracteristicaProduto() {
        return caracteristicaProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "ProdutoResponseDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", instanteCadastro=" + instanteCadastro +
                ", descricao='" + descricao + '\'' +
                ", caracteristicaProduto=" + caracteristicaProduto +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                '}';
    }
}
