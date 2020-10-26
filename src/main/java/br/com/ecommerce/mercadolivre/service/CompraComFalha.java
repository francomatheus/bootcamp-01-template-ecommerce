package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.email.EmailFalhaCompraMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraComFalha {

    @Autowired
    private EmailFalhaCompraMock emailFalhaCompraMock;

    public void processaCompraComFalha(Compra compra){
        emailFalhaCompraMock.processa(compra);
    }
}
