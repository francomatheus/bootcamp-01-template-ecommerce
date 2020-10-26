package br.com.ecommerce.mercadolivre.service.email;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@Profile(value = "prod")
@Service
// +1
public class EmailCompraSucesso implements EventosCompraSucesso {

    private static Logger logger = LoggerFactory.getLogger(EmailCompraSucesso.class);

    @Autowired
    // +1
    private SendMail sendMail;

    @Override
    // +1
    public void processa(Compra compra) {
        sendMail.send(compra.emailVendedor(), compra.emailComprador(), "Compra realizada com sucesso!!!");
    }

}
