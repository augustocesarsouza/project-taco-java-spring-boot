package com.calvin.klein.application.Services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorValidation {
    @JsonProperty("field")
    private String Field;

    @JsonProperty("message")
    private String Message;

    public ErrorValidation(String field, String message) {
        Field = field;
        Message = message;
    }
}
