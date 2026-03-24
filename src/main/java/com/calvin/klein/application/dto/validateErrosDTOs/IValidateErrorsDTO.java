package com.calvin.klein.application.dto.validateErrosDTOs;

import com.calvin.klein.application.Services.ErrorValidation;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface IValidateErrorsDTO {
    List<ErrorValidation> ValidateDTO(List<ObjectError> errorsDTO);
}
