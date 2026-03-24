package com.calvin.klein.application.dto.validateErrosDTOs;

import com.calvin.klein.application.Services.ErrorValidation;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateErrorsDTO implements IValidateErrorsDTO{
    @Override
    public List<ErrorValidation> ValidateDTO(List<ObjectError> errorsDTO) {
        List<ErrorValidation> errors = new ArrayList<>();

        errorsDTO.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            var errorValidation = new ErrorValidation(fieldName, message);
            errors.add(errorValidation);
        });

        return errors;
    }
}
