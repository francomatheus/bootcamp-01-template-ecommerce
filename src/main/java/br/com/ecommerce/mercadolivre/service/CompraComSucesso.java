package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CompraComSucesso{

    @Autowired
    private Set<EventosCompraSucesso> eventosCompraSucessos;

    @Autowired
    private CompraComFalha compraComFalha;

    public void realizaEventosCompraComSucesso(Compra compra) {
        //+1
        if (compra.processadaComSucesso()) {
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
