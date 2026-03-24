package com.calvin.klein.application.dto.validations.UserValidationDTOs;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final Pattern UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("\\d");
    private static final Pattern SPECIAL = Pattern.compile("[^a-zA-Z0-9]");
    private static final List<String> COMMON_WORDS = List.of("password", "senha", "admin", "123456", "qwerty", "abc123");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        // Mínimo de 6 caracteres
        if (password.length() < 6) {
            return false;
        }

        // Deve conter letras maiúsculas, minúsculas, número e especial
        if (!UPPERCASE.matcher(password).find() ||
                !LOWERCASE.matcher(password).find())
//                !DIGIT.matcher(password).find() ||
//                !SPECIAL.matcher(password).find())
        {
            return false;
        }

        // Evitar sequências simples
//        if (isSequential(password)) {
//            return false;
//        }

        // Evitar palavras comuns
        String lower = password.toLowerCase();
        for (String common : COMMON_WORDS) {
            if (lower.contains(common)) {
                return false;
            }
        }

        return true;
    }

    private boolean isSequential(String password) {
        String seq = "abcdefghijklmnopqrstuvwxyz";
        String seqNum = "0123456789";

        String lower = password.toLowerCase();

        for (int i = 0; i < seq.length() - 2; i++) {
            String part = seq.substring(i, i + 3);
            if (lower.contains(part)) {
                return true;
            }
        }

        for (int i = 0; i < seqNum.length() - 2; i++) {
            String part = seqNum.substring(i, i + 3);
            if (password.contains(part)) {
                return true;
            }
        }

        return false;
    }
}
