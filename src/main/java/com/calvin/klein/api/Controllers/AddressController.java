package com.calvin.klein.api.Controllers;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.calvin.klein.application.Services.ResultService;

import com.calvin.klein.application.Services.interfaces.IAddressService;
import com.calvin.klein.application.dto.AddressDTO;
import com.calvin.klein.application.dto.CreateUserDTO;
import com.calvin.klein.application.dto.validations.AddressValidationDTOs.AddressValidatorDTO;


@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class AddressController {
    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/public/address/test")
    public ResponseEntity<ResultService<AddressDTO>> TestHere(){
        var result = addressService.TestHere();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/address/get-info-address/{addressId}")
    public ResponseEntity<ResultService<AddressDTO>> GetInfoUser(@PathVariable String addressId){
        var result = addressService.GetInfoAddress(UUID.fromString(addressId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/address/get-info-address-by-user-id/{userId}")
    public ResponseEntity<ResultService<AddressDTO>> GetInfoAddressByUserId(@PathVariable String userId){
        var result = addressService.GetInfoAddressByUserId(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/address/create")
    public ResponseEntity<ResultService<AddressDTO>> Create(@Valid @RequestBody AddressValidatorDTO addressValidatorDTO, BindingResult resultValid){
        var result = addressService.create(addressValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
