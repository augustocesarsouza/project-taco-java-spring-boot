package com.calvin.klein.application.dto;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    public UUID id;
    public UUID userId;
    public UserDTO userDTO;
    public String country;
    public String cep;
    public String street;
    public String numberhouse;
    public String complemenet;
    public String neighborhood;
    public String city;
    public String stateName;
    public String recipientsName;

    public AddressDTO(){
    }

    public AddressDTO(UUID id, String country, String cep, String street, String numberhouse,
        String complemenet, String neighborhood, String city, String stateName, String recipientsName){
            this.id = id;
            this.country = country;
            this.cep = cep;
            this.street = street;
            this.numberhouse = numberhouse;
            this.complemenet = complemenet;
            this.neighborhood = neighborhood;
            this.city = city;
            this.stateName = stateName;
            this.recipientsName = recipientsName;
    }

    public AddressDTO(UUID id, UUID userId, UserDTO userDTO, String country, String cep, String street, String numberhouse,
        String complemenet, String neighborhood, String city, String stateName, String recipientsName){
            this.id = id;
            this.userId = userId;
            this.userDTO = userDTO;
            this.country = country;
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
