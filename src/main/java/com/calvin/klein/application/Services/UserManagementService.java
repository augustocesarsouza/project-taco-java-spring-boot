package com.calvin.klein.application.Services;

import com.calvin.klein.application.CodeRandomUser.Interface.ICodeRandomDictionary;
import com.calvin.klein.application.CodeRandomUser.Interface.IQuantityAttemptChangePasswordDictionary;
import com.calvin.klein.application.Services.interfaces.IUserManagementService;
import com.calvin.klein.application.dto.CodeReturn;
import com.calvin.klein.application.dto.CreateUserDTO;
import com.calvin.klein.application.dto.UserDTO;
import com.calvin.klein.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.calvin.klein.application.dto.validations.UserValidationDTOs.UserCreateValidatorDTO;
import com.calvin.klein.application.dto.validations.UserValidationDTOs.UserUpdateValidatorDTO;
import com.calvin.klein.application.util.interfaces.IBCryptPasswordEncoderUtil;
import com.calvin.klein.application.util.interfaces.IDictionaryCode;
import com.calvin.klein.data.utilityExternal.Interface.ISendEmailUser;
import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.authentication.ITokenGenerator;
import com.calvin.klein.domain.authentication.TokenOutValue;
import com.calvin.klein.domain.entities.User;
import com.calvin.klein.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserManagementService implements IUserManagementService {
    private final IUserRepository userRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final IBCryptPasswordEncoderUtil bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final ITokenGenerator tokenGenerator;
    private final ICodeRandomDictionary codeRandomDictionary;
    private final IQuantityAttemptChangePasswordDictionary quantityAttemptChangePasswordDictionary;
    private final ISendEmailUser sendEmailUser;
    private final IDictionaryCode dictionaryCode;

    @Autowired
    public UserManagementService(IUserRepository userRepository, IValidateErrorsDTO validateErrorsDTO,
                                 IBCryptPasswordEncoderUtil bCryptPasswordEncoder, ModelMapper modelMapper,
                                 ITokenGenerator tokenGenerator, ICodeRandomDictionary codeRandomDictionary,
                                 IQuantityAttemptChangePasswordDictionary quantityAttemptChangePasswordDictionary,
                                 ISendEmailUser sendEmailUser, IDictionaryCode dictionaryCode) {
        this.userRepository = userRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.tokenGenerator = tokenGenerator;
        this.codeRandomDictionary = codeRandomDictionary;
        this.quantityAttemptChangePasswordDictionary = quantityAttemptChangePasswordDictionary;
        this.sendEmailUser = sendEmailUser;
        this.dictionaryCode = dictionaryCode;
    }

    @Override
    public ResultService<UserDTO> GetInfoUser(UUID userId) {
        try {
            var userhere = userRepository.GetInfoUser(userId);

            if(userhere == null)
                return ResultService.Fail("not found user");

            var userDto = modelMapper.map(userhere, UserDTO.class);
            return ResultService.Ok(userDto);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CreateUserDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result) {
        if(userCreateValidatorDTO == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        User userCreate = new User();

        try {
            var tokenForCreation = userCreateValidatorDTO.getTokenForCreation();
            var email = userCreateValidatorDTO.getEmail();

            if(tokenForCreation != null){
                var valueContain = codeRandomDictionary.Container(email, tokenForCreation);
            }

            // esse token é quando manda para email do usuario tem que ser o mesmo token que la no frontend eles manda o cod para email

            var createUserDTO = new CreateUserDTO(false, null);

//            desconmentar para precisar d ocodigo para criar - faça o angular amanha o front e no proximo sabado continua no login
//            if (!valueContain)
//            {
//                // token errado retorna um erro personalizado da para criar um "DTO" que contem tipo Se o token está certo e o "DTO"
//
//                return ResultService.Ok(createUserDTO);
//            }

            String password = userCreateValidatorDTO.getPassword();
            UUID uuid_user_id = UUID.randomUUID();
            String passwordEncoder = bCryptPasswordEncoder.encodePassword(password);

            var verifyIfUserExist = userRepository.VerifyIfEmailExist(email);

            if(verifyIfUserExist != null)
                return ResultService.Fail("user already exist");

            userCreate = new User(uuid_user_id, userCreateValidatorDTO.getEmail(), passwordEncoder);

            var userData = userRepository.create(userCreate);
            userData.setPasswordHash(null);
            var userMap = modelMapper.map(userData, UserDTO.class);

            createUserDTO.setTokenIsValid(true);
            createUserDTO.setUserDTO(userMap);

            return ResultService.Ok(createUserDTO);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<String> sendCodeEmailCreateAccount(String email) {
        if(email == null)
            return ResultService.Fail("Email Null");

        if(email.isEmpty())
            return ResultService.Fail("Email empty 0");

        try {
//            var user = userRepository.getByEmailInfoForSendTokenChangePassword(email);
//
//            if(user == null)
//                return ResultService.Fail("user not found");

            int randomCode = generateRandomNumber();

            if(dictionaryCode.getKeyDictionary(email) != null)
                dictionaryCode.removeKeyDictionary(email);

            dictionaryCode.putKeyValueDictionary(email, randomCode);

            var resultSend = sendEmailUser.sendCodeRandomString(randomCode, email);

            if(!resultSend.IsSuccess)
                return ResultService.Fail(resultSend.Message);

            return ResultService.Ok(resultSend.Message);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    private static int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    @Override
    @Transactional
    public ResultService<CodeReturn> VerifyCodeSentToEmail(String email, String code) {
        if(email == null)
            return ResultService.Fail(new CodeReturn<>(false, "Email Null"));

        if(!email.contains("@"))
            return ResultService.Fail(new CodeReturn<>(false, "Email Is invalid"));

        if(code == null)
            return ResultService.Fail(new CodeReturn<>(false, "Code Null"));

        if(code.length() < 6)
            return ResultService.Fail(new CodeReturn<>(false, "code invalid"));

        try {
//            var checkHere = dictionaryCode.getKeyDictionary(email);
//
//            if(checkHere == null)
//                return ResultService.Fail(new CodeReturn<>(false, "key not found"));

//            Integer convertToInt = Integer.parseInt(code);
//
//            if(!checkHere.equals(convertToInt))
//                return ResultService.Fail(new CodeReturn<>(false, "code not match"));

            User verifyIfUserExist = userRepository.VerifyIfEmailExist(email);

            InfoErrors<TokenOutValue> tokenOut = tokenGenerator.generatorTokenUser(verifyIfUserExist);

            if(!tokenOut.IsSuccess)
                return ResultService.Fail(tokenOut.Message);

            if(verifyIfUserExist != null){
                if(verifyIfUserExist.getPasswordHash() != null)
                    return ResultService.Fail(new CodeReturn<>(false, "user already exist"));
            }else {
                UUID uuid_user_id = UUID.randomUUID();
                User userCreate = new User(uuid_user_id, email);

                var userData = userRepository.create(userCreate);

                var userMap = modelMapper.map(userData, UserDTO.class);

                userMap.setToken(tokenOut.Data.getAccess_Token());

                return ResultService.Ok(new CodeReturn<>(true, userMap));
            }

            var userDTO = modelMapper.map(verifyIfUserExist, UserDTO.class);

            if(userDTO == null)
                return ResultService.Fail("error in null class mapping");

            userDTO.setToken(tokenOut.Data.getAccess_Token());

            return ResultService.Ok(new CodeReturn<>(true, userDTO));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }

    }

    @Override
    public ResultService<UserDTO> update(UserUpdateValidatorDTO userUpdateValidatorDTO, BindingResult resultValid) {
        if(userUpdateValidatorDTO == null)
            return ResultService.Fail("obj null");

        if(resultValid.hasErrors()){
            var errorsDTO = resultValid.getAllErrors();
            List<ErrorValidation> errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var user = userRepository.getByIdOnlyEmailOrCpfId(UUID.fromString(userUpdateValidatorDTO.getId()));
            if(user == null)
                return ResultService.Fail("user not found");

            user.setName(userUpdateValidatorDTO.getFirstName());
            user.setLastName(userUpdateValidatorDTO.getLastName());
            user.setCpf(userUpdateValidatorDTO.getCpf());
            user.setGender(userUpdateValidatorDTO.getGender());
            user.setTelephone(userUpdateValidatorDTO.getCellPhone());

            String birthDateStr = userUpdateValidatorDTO.getBirthDate();
            if(birthDateStr != null && !birthDateStr.isEmpty()){
                LocalDate dateOfBirth = LocalDate.parse(birthDateStr);
                user.setDateOfBirth(dateOfBirth);
            }

            var userChange = userRepository.update(user);

            if(userChange == null)
                return ResultService.Fail("error when updating user");

            return ResultService.Ok(modelMapper.map(userChange, UserDTO.class));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
