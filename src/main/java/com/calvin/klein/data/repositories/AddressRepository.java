package com.calvin.klein.data.repositories;

import org.springframework.stereotype.Component;
import com.calvin.klein.domain.entities.Address;
import com.calvin.klein.data.context.AddressRepositoryJPA;


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
    public Address GetInfoAddress(UUID addressId) {
        return addressRepositoryJPA.GetInfoAddress(addressId);
    }
}
