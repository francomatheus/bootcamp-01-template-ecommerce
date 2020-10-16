package br.com.ecommerce.mercadolivre.handler;

import java.time.LocalDateTime;
import java.util.List;

public class ErroPadraoAPI {

    private LocalDateTime instante;
    private String status;
    private List<String> erros;
    private String pilhaErro;

    public ErroPadraoAPI(LocalDateTime instante, String status, List<String> erros, String pilhaErro) {
        this.instante = instante;
        this.status = status;
        this.erros = erros;
        this.pilhaErro = pilhaErro;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getErros() {
        return erros;
    }

    public String getPilhaErro() {
        return pilhaErro;
    }

    @Override
    public String toString() {
        return "ErroPadraoAPI{" +
                "instante=" + instante +
                ", status='" + status + '\'' +
                ", erros=" + erros +
                ", pilhaErro='" + pilhaErro + '\'' +
                '}';
    }
}
