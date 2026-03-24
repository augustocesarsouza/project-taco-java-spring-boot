package com.calvin.klein.application.Services.interfaces;

import com.calvin.klein.application.Services.ResultService;
import com.calvin.klein.application.dto.CodeReturn;
import com.calvin.klein.application.dto.CreateUserDTO;
import com.calvin.klein.application.dto.UserDTO;
import com.calvin.klein.application.dto.validations.UserValidationDTOs.UserCreateValidatorDTO;
import com.calvin.klein.application.dto.validations.UserValidationDTOs.UserUpdateValidatorDTO;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IUserManagementService {
    ResultService<UserDTO> GetInfoUser(UUID userId);
    ResultService<CreateUserDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
    ResultService<String> sendCodeEmailCreateAccount(String email);
    ResultService<CodeReturn> VerifyCodeSentToEmail(String email, String code);
    ResultService<UserDTO> update(UserUpdateValidatorDTO userUpdateValidatorDTO, BindingResult resultValid);
}
