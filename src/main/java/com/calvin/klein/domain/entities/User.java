package com.calvin.klein.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_users", schema = "public")
public class User implements UserDetails {
    @jakarta.persistence.Id
    @Column(name = "user_id")
    @JsonProperty("id")
    private UUID Id;

    @Column(name = "login")
    @JsonProperty("login")
    private String Login;

    @Column(name = "name")
    @JsonProperty("name")
    private String Name;

    @Column(name = "last_name")
    @JsonProperty("lastName")
    private String LastName;

    @Column(name = "time_zone")
    @JsonProperty("timeZone")
    private ZoneId TimeZone;

    @Column(name = "cpf")
    @JsonProperty("cpf")
    private String Cpf;

    @Column(name = "date_of_birth")
    @JsonProperty("dateOfBirth")
    private LocalDate DateOfBirth;

    @Column(name = "telephone")
    @JsonProperty("telephone")
    private String Telephone;

    @Column(name = "email")
    @JsonProperty("email")
    private String Email;

    @Column(name = "password_hash")
    @JsonProperty("passwordHash")
    private String PasswordHash;

    @Column(name = "user_image")
    @JsonProperty("userImage")
    private String UserImage;

    @Column(name = "gender")
    @JsonProperty("gender")
    private String Gender;

    public User() {
    }

    public User(UUID id, String email) {
        Id = id;
        Email = email;
    }

    public User(UUID id, String email, String passwordHash) {
        Id = id;
        Email = email;
        PasswordHash = passwordHash;
    }

    public User(UUID id, String login, String name, String lastName, ZoneId timeZone,
                String cpf, LocalDate dateOfBirth, String telephone,
                String email, String passwordHash, String userImage, String gender) {
        Id = id;
        Login = login;
        Name = name;
        LastName = lastName;
        TimeZone = timeZone;
        Cpf = cpf;
        DateOfBirth = dateOfBirth;
        Telephone = telephone;
        Email = email;
        PasswordHash = passwordHash;
        UserImage = userImage;
        Gender = gender;
    }

    public void addData(String name, String lastName, String cpf, LocalDate dateOfBirth, String telephone, String gender) {
        Name = name;
        LastName = lastName;
        Cpf = cpf;
        DateOfBirth = dateOfBirth;
        Telephone = telephone;
        Gender = gender;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public @Nullable String getPassword() {
        return PasswordHash;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return Email;
    }
}
