package br.com.ecommerce.mercadolivre.domain.model;

import br.com.ecommerce.mercadolivre.service.SendMail;

import javax.validation.constraints.NotBlank;

public class MensagemEmail {

    private String from;
    private String to;
    private String mensagem;

    public MensagemEmail(@NotBlank String from, @NotBlank String to, @NotBlank String mensagem) {
        this.from = from;
        this.to = to;
        this.mensagem = mensagem;
    }

    public void enviarEmail(SendMail sendMail){
        sendMail.send(this.from,this.to,this.mensagem);
    }
}
