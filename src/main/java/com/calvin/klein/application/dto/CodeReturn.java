package com.calvin.klein.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeReturn<T> {
    @JsonProperty("codeValid")
    public boolean CodeValid;
    @JsonProperty("message")
    public String Message;
    @JsonProperty("data")
    public T Data;

    public CodeReturn(boolean codeValid, String message) {
        CodeValid = codeValid;
        Message = message;
    }

    public CodeReturn(boolean codeValid, T data) {
        CodeValid = codeValid;
        Data = data;
    }
}
