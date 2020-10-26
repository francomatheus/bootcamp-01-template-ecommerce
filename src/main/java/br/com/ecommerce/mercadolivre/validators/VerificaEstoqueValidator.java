package br.com.ecommerce.mercadolivre.validators;

import br.com.ecommerce.mercadolivre.domain.model.Produto;
import br.com.ecommerce.mercadolivre.domain.request.CompraFinalizadaRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 3
 */

@Component
public class VerificaEstoqueValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraFinalizadaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // +1
        CompraFinalizadaRequest compraFinalizadaRequest = (CompraFinalizadaRequest) target;
        // +1
        Produto produto = manager.find(Produto.class, compraFinalizadaRequest.getProdutoId());
        //Assert.isTrue(produto != null, "Produto não encontrado no banco de dados!!");

        //+1
        if(produto != null
                && compraFinalizadaRequest.getQuantidade() != null
                && compraFinalizadaRequest.getQuantidade() > produto.getQuantidadeDisponivel()){
            errors.rejectValue("quantidade",null,"solicitada é maior que estoque!!");
        }

    }
}
