package br.com.ecommerce.mercadolivre.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender implements SendMail{

    private static Logger logger = LoggerFactory.getLogger(MailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String from, String to, String text) {
/*
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
*/
        logger.info("Email enviado de: {} , para: {}, com o texto: {}", from,to,text);

    }
}
