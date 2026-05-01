package com.calvin.klein.data.repositories;

import org.springframework.stereotype.Component;
import com.calvin.klein.domain.entities.Address;
import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.data.context.AddressRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;

import com.calvin.klein.domain.repositories.IAddressRepository;

import java.util.UUID;

@Component
public class AddressRepository implements IAddressRepository {
    private final AddressRepositoryJPA addressRepositoryJPA;

    @Autowired
    public AddressRepository(AddressRepositoryJPA addressRepositoryJPA){
        this.addressRepositoryJPA = addressRepositoryJPA;
    }

    @Override
    public AddressDTO GetInfoAddress(UUID addressId) {
        return addressRepositoryJPA.GetInfoAddress(addressId);
    }

    @Override
    public AddressDTO GetInfoAddressByUserId(UUID userId) {
        return addressRepositoryJPA.GetInfoAddressByUserId(userId);
    }

    @Override
    public Address create(Address address) {
        if(address == null)
            return null;

        return addressRepositoryJPA.save(address);
    }
}
