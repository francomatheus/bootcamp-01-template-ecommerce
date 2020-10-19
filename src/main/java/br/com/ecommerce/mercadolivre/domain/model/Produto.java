package br.com.ecommerce.mercadolivre.domain.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal preco;
    @NotNull @Positive
    private Integer quantidadeDisponivel;
    @NotNull
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @NotBlank
    private String descricao;
    @Size(min = 3) @NotNull
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicaProduto;
    @NotNull
    @OneToOne
    private Categoria categoria;

    public Produto() {
    }

    public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal preco, @Positive @NotNull Integer quantidadeDisponivel, @NotBlank @Length(max = 1000) String descricao, Set<CaracteristicaProduto> caracteristicaProduto, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicaProduto = caracteristicaProduto;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
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

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaProduto> getCaracteristicaProduto() {
        return caracteristicaProduto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", caracteristicaProduto=" + caracteristicaProduto +
                ", categoria=" + categoria +
                '}';
    }
}
