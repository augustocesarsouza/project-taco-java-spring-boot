package com.calvin.klein.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    public UUID id;
    public String login;
    public String name;
    public String lastName;
    public ZoneId timeZone;
    public String cpf;
    public LocalDate dateOfBirth;
    public String telephone;
    public String email;
    public String passwordHash;
    public String token;
    public Integer tokenForCreation;
    public String password;
    public String birthDateString;
    public String userImage;
    public String gender;

    public UserDTO() {
    }

    public UserDTO(UUID id, String login, String name, String lastName, ZoneId timeZone,
                   String cpf, LocalDate dateOfBirth, String telephone, String email, String passwordHash,
                   String token, Integer tokenForCreation, String password, String birthDateString, String userImage, String gender) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.timeZone = timeZone;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.token = token;
        this.tokenForCreation = tokenForCreation;
        this.password = password;
        this.birthDateString = birthDateString;
        this.userImage = userImage;
        this.gender = gender;
    }
}
