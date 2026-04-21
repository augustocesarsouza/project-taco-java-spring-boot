package com.calvin.klein.application.Services;

import com.calvin.klein.application.Services.interfaces.IAddressService;
import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.calvin.klein.application.dto.validations.AddressValidationDTOs.AddressValidatorDTO;
import com.calvin.klein.domain.repositories.IAddressRepository;
import org.springframework.validation.BindingResult;
import com.calvin.klein.domain.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {
    private final IAddressRepository addressRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressService(IAddressRepository addressRepository, IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper){
        this.addressRepository = addressRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<AddressDTO> TestHere(){
        try {
            var addressDTOH = new AddressDTO();
            addressDTOH.setCity("seila");
            return ResultService.Ok(addressDTOH);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<AddressDTO> GetInfoAddress(UUID addressId){
        try {
            AddressDTO addressDto = addressRepository.GetInfoAddress(addressId);

            if(addressDto == null)
                return ResultService.Fail("not found address");

            // var addressDto = modelMapper.map(address, AddressDTO.class);
            return ResultService.Ok(addressDto);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<AddressDTO> create(AddressValidatorDTO addressValidatorDTO, BindingResult result) {
        if(addressValidatorDTO == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            UUID uuid_address_id = UUID.randomUUID();
            var addressCreate = new Address(uuid_address_id, UUID.fromString(addressValidatorDTO.userId), null,
                addressValidatorDTO.country, addressValidatorDTO.cep,addressValidatorDTO.street, 
                addressValidatorDTO.numberhouse, addressValidatorDTO.complemenet,
                addressValidatorDTO.neighborhood, addressValidatorDTO.city, addressValidatorDTO.stateName, 
                addressValidatorDTO.recipientsName
            );

            var addressCreateReturn = addressRepository.create(addressCreate);

            AddressDTO addressMap = modelMapper.map(addressCreateReturn, AddressDTO.class);

            return ResultService.Ok(addressMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}

// public Address(UUID id, String country, String cep, String street, String numberhouse,
//         String complemenet, String neighborhood, String city, String stateName, String recipientsName)