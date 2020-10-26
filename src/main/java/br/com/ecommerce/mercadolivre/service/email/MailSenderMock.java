package br.com.ecommerce.mercadolivre.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 1
 */


@Service
@Profile("dev")
// +1
public class MailSenderMock implements SendMail{

    private static Logger logger = LoggerFactory.getLogger(MailSenderMock.class);

    @Override
    public void send(String from, String to, String text) {

        logger.info("Email enviado de: {} , para: {}, com o texto: {}", from,to,text);

    }
}
