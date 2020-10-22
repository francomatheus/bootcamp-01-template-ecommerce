package br.com.ecommerce.mercadolivre.domain.model;

import br.com.ecommerce.mercadolivre.domain.enums.StatusCompra;
import br.com.ecommerce.mercadolivre.domain.enums.TipoPagamento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "compra")
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Positive
    private final Integer quantidade;
    @Enumerated(EnumType.STRING)
    private final TipoPagamento formaPagamento;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    @ManyToOne @NotNull
    private final Usuario usuario;
    @ManyToOne @NotNull
    private final Produto produto;

    public Compra(@NotNull @Positive Integer quantidade, @NotNull TipoPagamento formaPagamento,StatusCompra statusCompra, Usuario usuario, Produto produto) {
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.statusCompra = statusCompra;
        this.usuario = usuario;
        this.produto = produto;
    }

    public void setStatusCompra(StatusCompra statusCompra) {
        this.statusCompra = statusCompra;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", formaPagamento=" + formaPagamento +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public TipoPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Long getProdutoId(){
        return produto.getId();
    }
}
