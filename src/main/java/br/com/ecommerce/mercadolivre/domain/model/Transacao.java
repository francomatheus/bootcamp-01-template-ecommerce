package br.com.ecommerce.mercadolivre.domain.model;

import br.com.ecommerce.mercadolivre.domain.enums.StatusTransacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao statusCompra;
    @NotBlank
    private String transacaoId;
    @NotNull
    @ManyToOne
    private Compra compra;
    @NotNull
    private LocalDateTime instante;

    public Transacao() {
    }

    public Transacao(StatusTransacao statusCompra, String transacaoId, Compra compra) {
        this.statusCompra = statusCompra;
        this.transacaoId = transacaoId;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    public Long getId() {
        return id;
    }

    public StatusTransacao getStatusCompra() {
        return statusCompra;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public Compra getCompra() {
        return compra;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(transacaoId, transacao.transacaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transacaoId);
    }

    public boolean concluidaComSucesso(Transacao transacao) {
        return this.statusCompra.equals(StatusTransacao.sucesso);
    }
}
