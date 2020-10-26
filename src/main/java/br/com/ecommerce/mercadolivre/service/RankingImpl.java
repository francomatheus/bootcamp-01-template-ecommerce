package br.com.ecommerce.mercadolivre.service;

import br.com.ecommerce.mercadolivre.consumer.Ranking;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */


@Service
// +1
public class RankingImpl implements EventosCompraSucesso {

    @Autowired
    // +1
    private Ranking ranking;

    @Override
    // +1
    public void processa(Compra compra) {
        ranking.processa(Map.of("compraId",compra.getId(),"vendedorId",compra.vendedorId()));
    }
}
