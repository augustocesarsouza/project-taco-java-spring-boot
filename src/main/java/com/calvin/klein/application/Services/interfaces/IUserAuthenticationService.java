package com.calvin.klein.application.Services.interfaces;

import com.calvin.klein.application.Services.ResultService;
import com.calvin.klein.application.dto.UserLoginDTO;

public interface IUserAuthenticationService {
    ResultService<UserLoginDTO> Login(String email, String password);
}
