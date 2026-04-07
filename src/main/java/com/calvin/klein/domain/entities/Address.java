package com.calvin.klein.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_address", schema = "public")
public class Address {
    @jakarta.persistence.Id
    @Column(name = "address_id")
    @JsonProperty("id")
    private UUID Id;

    @Column(name = "country")
    @JsonProperty("country")
    private String Country;

    @Column(name = "cep")
    @JsonProperty("cep")
    private String Cep;

    @Column(name = "street")
    @JsonProperty("street")
    private String Street;

    @Column(name = "number_house")
    @JsonProperty("numberhouse")
    private String Numberhouse;

    @Column(name = "complemenet")
    @JsonProperty("complemenet")
    private String Complemenet;

    @Column(name = "neighborhood")
    @JsonProperty("neighborhood")
    private String Neighborhood;

    @Column(name = "city")
    @JsonProperty("city")
    private String City;

    @Column(name = "state_name")
    @JsonProperty("stateName")
    private String StateName;

    @Column(name = "recipients_name")
    @JsonProperty("recipientsName")
    private String RecipientsName;

    public Address(UUID id, String country, String cep, String street, String numberhouse,
        String complemenet, String neighborhood, String city, String stateName, String recipientsName) {
        Id = id;
        Country = country;
        Cep = cep;
        Street = street;
        Numberhouse = numberhouse;
        Complemenet = complemenet;
        Neighborhood = neighborhood;
        City = city;
        StateName = stateName;
        RecipientsName = recipientsName;
    }
}
