package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.consumer.Ranking;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RankingImpl implements EventosCompraSucesso {

    @Autowired
    private Ranking ranking;

    @Override
    public void processa(Compra compra) {
        ranking.processa(Map.of("compraId",compra.getId(),"vendedorId",compra.vendedorId()));
    }
}
