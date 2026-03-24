package com.calvin.klein.data.repositories;

import com.calvin.klein.data.context.UserRepositoryJPA;
import com.calvin.klein.domain.entities.User;
import com.calvin.klein.domain.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRepository implements IUserRepository {
    private final UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UserRepository(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public User GetInfoUser(UUID userId) {
        return userRepositoryJPA.GetInfoUser(userId);
    }

    @Override
    public User GetUserInfoToLogin(String email) {
        return userRepositoryJPA.GetUserInfoToLogin(email);
    }

    @Override
    public User VerifyIfEmailExist(String email) {
        return userRepositoryJPA.VerifyIfEmailExist(email);
    }

    @Override
    public User create(User user) {
        if(user == null)
            return null;

        return userRepositoryJPA.save(user);
    }

    @Override
    public User GetUserByIdInfoEmailPasswordHash(UUID guidId) {
        return userRepositoryJPA.GetUserByIdInfoEmailPasswordHash(guidId);
    }

    @Override
    public User GetUserByEmailToUserAuthentication(String email) {
        return userRepositoryJPA.GetUserByEmailToUserAuthentication(email);
    }

    @Override
    public User getByEmailInfoForSendTokenChangePassword(String email) {
        return userRepositoryJPA.getByEmailInfoForSendTokenChangePassword(email);
    }

    @Override
    public User getByIdOnlyEmailOrCpfId(UUID guidId) {
        return userRepositoryJPA.getByIdOnlyEmailOrCpfId(guidId);
        //@Query("SELECT u.Id, u.Email, u.Cpf FROM User AS u WHERE u.Id = :userId")
    }

    @Override
    public User update(User user) {
        if(user == null)
            return null;

        User userToUpdate = userRepositoryJPA.findById(user.getId()).orElse(null);

        if(userToUpdate == null)
            return null;

        userToUpdate.addData(user.getName(), user.getLastName(), user.getCpf(), user.getDateOfBirth(), user.getTelephone(), user.getGender());

        return userRepositoryJPA.save(userToUpdate);
    }
}
