package com.calvin.klein.application.dto.validations.UserValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateValidatorDTO {
    @JsonProperty("id")
    public UUID id;

    @JsonProperty("login")
    private String login;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("timeZone")
    private String timeZone; //ZoneId

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("telephone")
    private String telephone;

    @NotEmpty(message = "email should not be empty")
    @Size(min = 8, message = "email should have at last 8 characters")
    @JsonProperty("email")
    private String email;

    @JsonProperty("token")
    public String token;

    @JsonProperty("tokenForCreation")
    public Integer tokenForCreation;

    @ValidPassword
    @JsonProperty("password")
    public String password;

    @JsonProperty("birthDateString")
    public String birthDateString;

    @JsonProperty("userImage")
    private String UserImage;

    public UserCreateValidatorDTO() {
    }

    public UserCreateValidatorDTO(UUID id, String login, String name, String lastName, String timeZone,
                                  String cpf, String telephone, String email, String token, Integer tokenForCreation,
                                  String password, String birthDateString, String userImage) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.timeZone = timeZone;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
        this.token = token;
        this.tokenForCreation = tokenForCreation;
        this.password = password;
        this.birthDateString = birthDateString;
        this.UserImage = userImage;
    }
}
