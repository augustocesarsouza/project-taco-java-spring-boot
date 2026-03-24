package com.calvin.klein.domain.authentication;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.entities.User;

public interface ITokenGenerator {
    InfoErrors<TokenOutValue> generatorTokenUser(User user);
    Claim getClaimUserId(String token) throws TokenExpiredException;
    InfoErrors<TokenOutValue> generatorTokenUrlChangeEmail(User user);
}
