package com.calvin.klein.data.context;

import org.springframework.stereotype.Repository;

import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

@Repository
public interface AddressRepositoryJPA extends JpaRepository<Address, UUID> {

    @Query("SELECT new com.calvin.klein.application.dto." +
            "AddressDTO(x.Id, null, new com.calvin.klein.application.dto.UserDTO(null, null, x.User.Name, x.User.LastName, null, " +
            "null, null, null, x.User.Email, null, null, null, null, null, null, null), " +
            "x.Country, x.Cep, x.Street, x.Numberhouse, x.Complemenet, x.Neighborhood, x.City, x.StateName, x.RecipientsName) " +
            "FROM Address AS x " +
            "WHERE x.Id = :addressId")
    AddressDTO GetInfoAddress(UUID addressId);

}

// AddressDTO(UUID id, UUID userId, UserDTO userDTO, String country, String cep, String street, String numberhouse,
//      String complemenet, String neighborhood, String city, String stateName, String recipientsName)

// UserDTO(UUID id, String login, String name, String lastName, ZoneId timeZone,
//                    String cpf, LocalDate dateOfBirth, String telephone, String email, String passwordHash,
//                    String token, Integer tokenForCreation, String password, String birthDateString, String userImage, String gender) 