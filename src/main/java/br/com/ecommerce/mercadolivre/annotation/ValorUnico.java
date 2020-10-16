package br.com.ecommerce.mercadolivre.annotation;

import br.com.ecommerce.mercadolivre.validators.ValorUnicoNoBancoDeDadosValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorUnicoNoBancoDeDadosValidator.class)
public @interface ValorUnico {

    String message() default "{javax.validation.constraints.ValorUnico.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> className();

    String fieldName();
}
