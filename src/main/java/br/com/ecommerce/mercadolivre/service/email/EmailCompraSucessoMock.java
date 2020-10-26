package br.com.ecommerce.mercadolivre.service.email;

import br.com.ecommerce.mercadolivre.domain.model.Compra;
import br.com.ecommerce.mercadolivre.service.EventosCompraSucesso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(value = "dev")
@Service
public class EmailCompraSucessoMock implements EventosCompraSucesso {

    private static Logger logger = LoggerFactory.getLogger(EmailCompraSucessoMock.class);

    @Autowired
    private SendMail sendMail;

    @Override
    public void processa(Compra compra) {
        sendMail.send(compra.emailVendedor(), compra.emailComprador(), "Compra realizada com sucesso!!!");
    }

}
