package com.calvin.klein.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
    private String login;
    private String name;
    private String lastName;
    private ZoneId timeZone;
    private String cpf;
    private LocalDate dateOfBirth;
    private String telephone;
    private String email;
    private String passwordHash;
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
