package com.calvin.klein.api.Controllers;

import com.calvin.klein.application.Services.ResultService;
import com.calvin.klein.application.Services.interfaces.IUserAuthenticationService;
import com.calvin.klein.application.Services.interfaces.IUserManagementService;
import com.calvin.klein.application.dto.CodeReturn;
import com.calvin.klein.application.dto.CreateUserDTO;
import com.calvin.klein.application.dto.UserDTO;
import com.calvin.klein.application.dto.UserLoginDTO;
import com.calvin.klein.application.dto.validations.UserValidationDTOs.UserCreateValidatorDTO;
import com.calvin.klein.application.dto.validations.UserValidationDTOs.UserUpdateValidatorDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class UserController {
    private final IUserAuthenticationService userAuthenticationService;
    private final IUserManagementService userManagementService;

    @Autowired
    public UserController(IUserAuthenticationService userAuthenticationService, IUserManagementService userManagementService) {
        this.userAuthenticationService = userAuthenticationService;
        this.userManagementService = userManagementService;
    }

    @GetMapping("/public/user/login/{email}/{password}")
    public ResponseEntity<ResultService<UserLoginDTO>> Login(@PathVariable String email, @PathVariable String password){
        var result = userAuthenticationService.Login(email, password);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/user/create")
    public ResponseEntity<ResultService<CreateUserDTO>> Create(@Valid @RequestBody UserCreateValidatorDTO userCreateValidatorDTO, BindingResult resultValid){
        var result = userManagementService.create(userCreateValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/user/get-info-user/{userId}")
    public ResponseEntity<ResultService<UserDTO>> GetInfoUser(@PathVariable String userId){
        var result = userManagementService.GetInfoUser(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/public/user/send-code-email-to-create-account/{email}")
    public ResponseEntity<ResultService<String>> SendCodeEmail(@PathVariable String email){
        var result = userManagementService.sendCodeEmailCreateAccount(email);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/public/user/verify-code-sent-to-email/{email}/{code}")
    public ResponseEntity<ResultService<CodeReturn>> VerifyCodeSentToEmail(@PathVariable String email, @PathVariable String code){
        var result = userManagementService.VerifyCodeSentToEmail(email, code);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/user/update-user")
    public ResponseEntity<ResultService<UserDTO>> update(@Valid @RequestBody UserUpdateValidatorDTO userUpdateValidatorDTO,
                                                         BindingResult resultValid){
        var result = userManagementService.update(userUpdateValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
