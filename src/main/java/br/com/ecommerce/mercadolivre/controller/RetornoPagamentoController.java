package br.com.ecommerce.mercadolivre.controller;

import br.com.ecommerce.mercadolivre.consumer.RetornoGatwayPagamento;
import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.domain.request.RetornoPagseguroRequest;
import br.com.ecommerce.mercadolivre.domain.request.RetornoPaypalRequest;
import br.com.ecommerce.mercadolivre.service.CompraComSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1/retorno-pagamento")
public class RetornoPagamentoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    // +1
    private CompraComSucesso compraComSucesso;

    @PostMapping("/pagseguro/{id}")
    @Transactional
    // +1
    public void retornoPagseguro(@PathVariable Long id, @RequestBody @Valid RetornoPagseguroRequest pagamentoRequest){
        processa(id,pagamentoRequest);

    }

    @PostMapping("/paypal/{id}")
    @Transactional
    // +1
    public void retornoPaypal(@PathVariable Long id, @RequestBody @Valid RetornoPaypalRequest pagamentoRequest){
        processa(id,pagamentoRequest);
    }

    // +1
    private void processa(Long compraId, RetornoGatwayPagamento retornoGatwayPagamento){
        // +1
        Compra compra = manager.find(Compra.class, compraId);
        compra.adicionaTransacao(retornoGatwayPagamento);
        // +1
        Assert.isTrue(compra != null , "Compra não encontrada!!!");

        manager.merge(compra);

        compraComSucesso.realizaEventosCompraComSucesso(compra);


        //return compra.toString();
    }

}

