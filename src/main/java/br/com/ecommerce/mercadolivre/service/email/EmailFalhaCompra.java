package br.com.ecommerce.mercadolivre.service.email;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class EmailFalhaCompra implements EventosCompraSucesso {

    @Autowired
    private SendMail sendMail;

    @Override
    public void processa(Compra compra) {
        sendMail.send(compra.emailVendedor(), compra.emailComprador(),
                "Falha no pagamento da sua compra. Favor, entrar em contato para que possamos resolver o problema e dar continuidade na compra!!");
    }
}
