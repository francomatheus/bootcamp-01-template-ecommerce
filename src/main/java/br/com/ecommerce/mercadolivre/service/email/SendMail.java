package br.com.ecommerce.mercadolivre.service.email;

public interface SendMail {

    void send(String from, String to, String text);
}
