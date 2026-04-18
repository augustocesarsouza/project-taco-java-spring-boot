package com.calvin.klein.data.context;

import org.springframework.stereotype.Repository;
import com.calvin.klein.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

@Repository
public interface AddressRepositoryJPA extends JpaRepository<Address, UUID> {

    @Query("SELECT new com.calvin.klein.domain.entities." +
            "Address(x.Id, x.Country, x.Cep, x.Street, x.Numberhouse, x.Complemenet, x.Neighborhood, x.City, x.StateName, x.RecipientsName) " +
            "FROM Address AS x " +
            "WHERE x.Id = :addressId")
    Address GetInfoAddress(UUID addressId);

}

// Address(UUID id, String country, String cep, String street, String numberhouse,
//         String complemenet, String neighborhood, String city, String stateName, String recipientsName)