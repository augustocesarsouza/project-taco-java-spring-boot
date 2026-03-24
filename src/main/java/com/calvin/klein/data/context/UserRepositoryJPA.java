package com.calvin.klein.data.context;

import com.calvin.klein.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, UUID> {
    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(x.Id, x.Login, x.Name, x.LastName, x.TimeZone, x.Cpf, x.DateOfBirth, x.Telephone, x.Email, null, x.UserImage, x.Gender) " +
            "FROM User AS x " +
            "WHERE x.Id = :userId")
    User GetInfoUser(UUID userId);

    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(x.Id, null, x.Name, x.LastName, null, x.Cpf, null, x.Telephone, x.Email, x.PasswordHash, null, null) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User GetUserInfoToLogin(String email);

    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(x.Id, null, x.Name, null, null, null, null, null, x.Email, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User VerifyIfEmailExist(String email);

    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(null, null, null, null, null, null, null, null, x.Email, x.PasswordHash, null, null) " +
            "FROM User AS x " +
            "WHERE x.Id = :userId")
    User GetUserByIdInfoEmailPasswordHash(UUID userId);

    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(x.Id, null, null, null, null, null, null, null, x.Email, x.PasswordHash, null, null) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User GetUserByEmailToUserAuthentication(String email);
//"User(u.Id, u.Name, u.Email, null, null, null) "
    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(x.Id, null, x.Name, null, null, null, null, null, x.Email, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User getByEmailInfoForSendTokenChangePassword(String email);

    @Query("SELECT new com.calvin.klein.domain.entities." +
            "User(x.Id, null, x.Name, null, null, x.Cpf, null, null, x.Email, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Id = :userId")
    User getByIdOnlyEmailOrCpfId(UUID userId);
}

//User(UUID id, String login, String name, String lastName, ZoneId timeZone,
//     String cpf, LocalDate dateOfBirth, String telephone, String email, String passwordHash, String userImage)