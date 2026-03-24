package com.calvin.klein.data.utilToken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForbiddenError {
    @JsonProperty("code")
    private String Code;
    @JsonProperty("message")
    private String Message;

    public ForbiddenError(String code, String message) {
        Code = code;
        Message = message;
    }

    public ForbiddenError() {
    }
}
