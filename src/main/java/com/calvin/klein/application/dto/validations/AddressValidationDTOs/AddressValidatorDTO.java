package com.calvin.klein.application.dto.validations.AddressValidationDTOs;

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
public class AddressValidatorDTO {
    public UUID id;

    @NotEmpty(message = "userId should not be empty")
    public String userId;

    @NotEmpty(message = "country should not be empty")
    public String country;

    @NotEmpty(message = "cep should not be empty")
    public String cep;

    @NotEmpty(message = "street should not be empty")
    public String street;

    @NotEmpty(message = "numberhouse should not be empty")
    public String numberhouse;

    @NotEmpty(message = "complemenet should not be empty")
    public String complemenet;

    @NotEmpty(message = "neighborhood should not be empty")
    public String neighborhood;

    @NotEmpty(message = "city should not be empty")
    public String city;

    @NotEmpty(message = "stateName should not be empty")
    public String stateName;

    @NotEmpty(message = "recipientsName should not be empty")
    public String recipientsName;

    public AddressValidatorDTO(String country, String userId, String cep, String street, String numberhouse, String complemenet,
        String neighborhood, String city, String stateName, String recipientsName){
            this.country = country;
            this.userId = userId;
            this.cep = cep;
            this.street = street;
            this.numberhouse = numberhouse;
            this.complemenet = complemenet;
            this.neighborhood = neighborhood;
            this.city = city;
            this.stateName = stateName;
            this.recipientsName = recipientsName;
    }

}
