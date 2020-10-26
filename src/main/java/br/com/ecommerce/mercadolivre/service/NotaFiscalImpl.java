package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.consumer.NotaFiscal;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotaFiscalImpl implements EventosCompraSucesso {

    @Autowired
    private NotaFiscal notaFiscal;

    @Override
    public void processa(Compra compra) {
        notaFiscal.processa(Map.of("compraId", compra.getId(),
                "compradorId", compra.compradorId()));
    }
}
