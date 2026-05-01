package com.calvin.klein.domain.repositories;

import java.util.UUID;

import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.domain.entities.Address;

public interface IAddressRepository {
    AddressDTO GetInfoAddress(UUID addressId);
    AddressDTO GetInfoAddressByUserId(UUID userId);
    Address create(Address address);
}
