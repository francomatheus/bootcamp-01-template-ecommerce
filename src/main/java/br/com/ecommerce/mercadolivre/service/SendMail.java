package br.com.ecommerce.mercadolivre.service;

public interface SendMail {

    void send(String from, String to, String text);
}
