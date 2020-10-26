package br.com.ecommerce.mercadolivre.validators;


import br.com.ecommerce.mercadolivre.annotation.ValorUnico;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Carga intrínseca máxima permitida - 7
 * Carga intrínseca da classe - 2
 */

public class ValorUnicoNoBancoDeDadosValidator implements ConstraintValidator<ValorUnico, String> {

    private Class<?> className;
    private String fieldName;

    @Autowired
    private EntityManager manager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        className = constraintAnnotation.className();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // +1
        List<?> resultQuery = manager.createQuery("select u from " + className.getName() + " u where u." + fieldName + "= :value")
                .setParameter("value", value).getResultList();
        // +1
        if (resultQuery.size()>0){
            return false;
        }

        return true;
    }
}
