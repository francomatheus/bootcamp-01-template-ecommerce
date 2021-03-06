package br.com.ecommerce.mercadolivre.domain.model;

import br.com.ecommerce.mercadolivre.consumer.RetornoGatwayPagamento;
import br.com.ecommerce.mercadolivre.domain.enums.StatusCompra;
import br.com.ecommerce.mercadolivre.domain.enums.TipoPagamento;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Carga intrínseca máxima permitida - 9
 * Carga intrínseca da classe - 11
 */

@Entity
@Table(name = "compra")
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Positive
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    // +1
    private TipoPagamento formaPagamento;
    @Enumerated(EnumType.STRING)
    // +1
    private StatusCompra statusCompra;
    @ManyToOne @NotNull
    // +1
    private Usuario usuario;
    @ManyToOne @NotNull
    // +1
    private Produto produto;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    // +1
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Positive Integer quantidade, @NotNull TipoPagamento formaPagamento, StatusCompra statusCompra, Usuario usuario, Produto produto) {
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

    public Integer getQuantidade() {
        return quantidade;
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

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public Long compradorId(){
        return this.usuario.getId();
    }

    public Long vendedorId(){
        return this.produto.donoProdutoId();
    }

    public String emailComprador(){
        return this.usuario.getLogin();
    }

    public String emailVendedor(){
        return this.produto.donoDoProduto();
    }
    // +1
    public void adicionaTransacao(@Valid RetornoGatwayPagamento retornoGatwayPagamento) {
        Transacao novaTransacao = retornoGatwayPagamento.toTransacao(this);
        // +1
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação igual a essa processada");
        Set<Transacao> transacoesConcluidasComSucesso = transacoesConcluidasComSucesso();
        // +1
        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluida com Sucesso !!!");

        this.transacoes.add(novaTransacao);

    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                // +1
                .filter(transacao -> transacao.concluidaComSucesso(transacao))
                // +1
                .collect(Collectors.toSet());
        // +1
        Assert.isTrue(transacoesConcluidasComSucesso.size() <=1,"Tem mais de uma transação concluida com sucesso");
        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();

    }
}
