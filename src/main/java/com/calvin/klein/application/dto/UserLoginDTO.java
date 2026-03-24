package com.calvin.klein.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    @JsonProperty("passwordIsCorrect")
    public boolean PasswordIsCorrect;
    @JsonProperty("userDTO")
    public UserDTO UserDTO;

    public UserLoginDTO() {
    }

    public UserLoginDTO(boolean passwordIsCorrect, UserDTO userDTO) {
        PasswordIsCorrect = passwordIsCorrect;
        UserDTO = userDTO;
    }
}
