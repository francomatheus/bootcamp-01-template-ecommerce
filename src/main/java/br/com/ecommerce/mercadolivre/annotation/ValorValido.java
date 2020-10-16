package br.com.ecommerce.mercadolivre.annotation;

import br.com.ecommerce.mercadolivre.validators.ValorExisteNoBancoDeDadosValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorExisteNoBancoDeDadosValidator.class)
public @interface ValorValido {

    String message() default "{javax.validation.constraints.ValorIdNaoEncontrado.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> className();
}
