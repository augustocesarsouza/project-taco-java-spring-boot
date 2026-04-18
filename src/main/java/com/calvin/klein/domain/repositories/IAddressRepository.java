package com.calvin.klein.domain.repositories;

import java.util.UUID;
import com.calvin.klein.domain.entities.Address;

public interface IAddressRepository {
    Address GetInfoAddress(UUID addressId);
    Address create(Address address);
}
