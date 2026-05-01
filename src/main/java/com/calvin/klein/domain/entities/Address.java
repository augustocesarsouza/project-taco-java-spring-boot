package com.calvin.klein.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Entity
@Table(name = "tb_address", schema = "public")
public class Address {
    @jakarta.persistence.Id
    @Column(name = "address_id")
    @JsonProperty("id")
    public UUID Id;

    @Column(name = "user_id")
    @JsonProperty("userId")
    public UUID UserId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public User User;

    @Column(name = "country")
    @JsonProperty("country")
    public String Country;

    @Column(name = "cep")
    @JsonProperty("cep")
    public String Cep;

    @Column(name = "street")
    @JsonProperty("street")
    public String Street;

    @Column(name = "number_house")
    @JsonProperty("numberhouse")
    public String Numberhouse;

    @Column(name = "complemenet")
    @JsonProperty("complemenet")
    public String Complemenet;

    @Column(name = "neighborhood")
    @JsonProperty("neighborhood")
    public String Neighborhood;

    @Column(name = "city")
    @JsonProperty("city")
    public String City;

    @Column(name = "state_name")
    @JsonProperty("stateName")
    public String StateName;

    @Column(name = "recipients_name")
    @JsonProperty("recipientsName")
    public String RecipientsName;

    public Address(){
    }

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

    public Address(UUID id, UUID userId, User user, String country, String cep, String street, String numberhouse,
        String complemenet, String neighborhood, String city, String stateName, String recipientsName) {
        Id = id;
        UserId = userId;
        User = user;
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
