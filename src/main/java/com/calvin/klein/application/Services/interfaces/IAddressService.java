package com.calvin.klein.application.Services.interfaces;
import java.util.UUID;

import com.calvin.klein.application.Services.ResultService;
import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.application.dto.validations.AddressValidationDTOs.AddressValidatorDTO;
import org.springframework.validation.BindingResult;

public interface IAddressService {
    ResultService<AddressDTO> TestHere();
    ResultService<AddressDTO> GetInfoAddress(UUID addressId);
    ResultService<AddressDTO> create(AddressValidatorDTO addressValidatorDTO, BindingResult result);
}
