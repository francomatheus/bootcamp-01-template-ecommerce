package br.com.ecommerce.mercadolivre.service.email;

/**
 * Carga intrínseca máxima permitida - 3
 * Carga intrínseca da classe - 0
 */


public interface SendMail {

    void send(String from, String to, String text);
}
