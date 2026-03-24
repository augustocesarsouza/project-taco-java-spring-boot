package com.calvin.klein.application.Services;

import com.calvin.klein.application.Services.interfaces.IUserAuthenticationService;
import com.calvin.klein.application.dto.UserDTO;
import com.calvin.klein.application.dto.UserLoginDTO;
import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.authentication.ITokenGenerator;
import com.calvin.klein.domain.authentication.TokenOutValue;
import com.calvin.klein.domain.entities.User;
import com.calvin.klein.domain.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {
    private final IUserRepository userRepository;
    private final ITokenGenerator tokenGenerator;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserAuthenticationService(IUserRepository userRepository, ITokenGenerator tokenGenerator,
                                     ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this. tokenGenerator = tokenGenerator;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResultService<UserLoginDTO> Login(String email, String password) {
        var userLoginDTO = new UserLoginDTO();

        try {
            User user = userRepository.GetUserInfoToLogin(email);

            if (user == null) return ResultService.Fail("Error user info login is null");

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User userAuth = (User) authenticate.getPrincipal();

            InfoErrors<TokenOutValue> tokenOut = tokenGenerator.generatorTokenUser(userAuth);

            if(!tokenOut.IsSuccess)
                return ResultService.Fail(tokenOut.Message);

            userAuth.setName(user.getName());
            userAuth.setEmail(user.getEmail());
            userAuth.setId(user.getId());
            userAuth.setPasswordHash(null);

            var userDTO = modelMapper.map(userAuth, UserDTO.class);

            if(userDTO == null)
                return ResultService.Fail("error in null class mapping");

            userDTO.setToken(tokenOut.Data.getAccess_Token());

            userLoginDTO.setPasswordIsCorrect(true);
            userLoginDTO.setUserDTO(userDTO);

            // TESTAR LOGIN DO USUARIO E fazer os outro metodos que tem que fazer do CONTROLLER

            return ResultService.Ok(userLoginDTO);
        } catch (Exception ex) {
            userLoginDTO.setPasswordIsCorrect(false);
            return ResultService.Fail(userLoginDTO);
        }
    }
}
