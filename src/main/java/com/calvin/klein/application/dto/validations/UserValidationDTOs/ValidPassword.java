package com.calvin.klein.application.dto.validations.UserValidationDTOs;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Senha inválida. A senha deve ter pelo menos 6 caracteres, incluir letras maiúsculas e minúsculas, números e caracteres especiais, e não conter sequências ou palavras comuns.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
