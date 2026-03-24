package com.calvin.klein.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserDTO {
    @JsonProperty("tokenIsValid")
    public boolean TokenIsValid;
    @JsonProperty("userDTO")
    public UserDTO UserDTO;

    public CreateUserDTO(boolean tokenIsValid, UserDTO userDTO) {
        TokenIsValid = tokenIsValid;
        UserDTO = userDTO;
    }
}
