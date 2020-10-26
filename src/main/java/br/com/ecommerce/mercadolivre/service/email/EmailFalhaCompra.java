package br.com.ecommerce.mercadolivre.service.email;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@Service
@Profile("prod")
// +1
public class EmailFalhaCompra implements EventosCompraSucesso {

    @Autowired
    // +1
    private SendMail sendMail;

    @Override
    // +1
    public void processa(Compra compra) {
        sendMail.send(compra.emailVendedor(), compra.emailComprador(),
                "Falha no pagamento da sua compra. Favor, entrar em contato para que possamos resolver o problema e dar continuidade na compra!!");
    }
}
