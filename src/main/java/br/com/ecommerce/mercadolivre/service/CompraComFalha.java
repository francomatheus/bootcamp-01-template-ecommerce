package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.email.EmailFalhaCompraMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 2
 */


@Service
public class CompraComFalha {

    @Autowired
    // +1
    private EmailFalhaCompraMock emailFalhaCompraMock;

    // +1
    public void processaCompraComFalha(Compra compra){
        emailFalhaCompraMock.processa(compra);
    }
}
