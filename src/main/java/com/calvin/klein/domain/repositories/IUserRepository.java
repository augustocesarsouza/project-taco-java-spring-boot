package com.calvin.klein.domain.repositories;

import com.calvin.klein.domain.entities.User;

import java.util.UUID;

public interface IUserRepository {
    User GetInfoUser(UUID userId);
    User GetUserInfoToLogin(String email);
    User VerifyIfEmailExist(String email);
    User create(User user);
    User GetUserByIdInfoEmailPasswordHash(UUID guidId);
    User GetUserByEmailToUserAuthentication(String email);
    User getByEmailInfoForSendTokenChangePassword(String email);
    User getByIdOnlyEmailOrCpfId(UUID guidId);
    User update(User user);
}
