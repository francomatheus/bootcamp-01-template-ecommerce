package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 6
 */


@Service
public class CompraComSucesso{

    @Autowired
    // +1
    private Set<EventosCompraSucesso> eventosCompraSucessos;

    @Autowired
    // +1
    private CompraComFalha compraComFalha;

    // +1
    public void realizaEventosCompraComSucesso(Compra compra) {
        //+1
        if (compra.processadaComSucesso()) {
            // +1
            eventosCompraSucessos.forEach(eventosCompraSucesso -> {
                eventosCompraSucesso.processa(compra);
            });
        }
        // +1
        else {
            compraComFalha.processaCompraComFalha(compra);
        }
    }

}
