package com.calvin.klein.application.dto.validations;

public class ReactionTypeException extends RuntimeException {
    public ReactionTypeException(int value) {
        super("ReactionType inválido: " + value + ". Deve ser 0 (NONE), 1 (LIKE) ou 2 (DISLIKE).");
    }
}