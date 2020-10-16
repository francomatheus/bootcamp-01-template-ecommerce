package br.com.ecommerce.mercadolivre.validators;

import br.com.ecommerce.mercadolivre.annotation.ValorValido;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorExisteNoBancoDeDadosValidator implements ConstraintValidator<ValorValido, Long> {

    private Class<?> className;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ValorValido constraintAnnotation) {
        className=constraintAnnotation.className();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        List<?> valorBuscadoPeloId = manager.createQuery("select c from " + className.getName() + " c where c.id =:value")
                .setParameter("value", value).getResultList();

        if (value==null || valorBuscadoPeloId.size()>0){
            return true;
        }

        return false;
    }
}
