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
public class UserUpdateValidatorDTO {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 36, message = "id should have at last 36 characters")
    @JsonProperty("id")
    private String Id;
    @JsonProperty("email")
    private String Email;
    @JsonProperty("firstName")
    private String FirstName;
    @JsonProperty("lastName")
    private String LastName;
    @JsonProperty("cpf")
    private String Cpf;
    @JsonProperty("gender")
    private String Gender;
    @JsonProperty("cellPhone")
    private String CellPhone;
    @JsonProperty("birthDate")
    private String BirthDate;

    public UserUpdateValidatorDTO(String email, String firstName, String lastName, String cpf, String gender, String cellPhone, String birthDate) {
        Email = email;
        FirstName = firstName;
        LastName = lastName;
        Cpf = cpf;
        Gender = gender;
        CellPhone = cellPhone;
        BirthDate = birthDate;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setCellPhone(String cellPhone) {
        CellPhone = cellPhone;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }
}
