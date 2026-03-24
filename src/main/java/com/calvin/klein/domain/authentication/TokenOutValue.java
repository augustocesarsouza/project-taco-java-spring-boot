package com.calvin.klein.domain.authentication;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenOutValue {
    private String Access_Token;
    private Date Expirations;

    public TokenOutValue(String access_Token, Date expirations) {
        Access_Token = access_Token;
        Expirations = expirations;
    }

    public TokenOutValue() {
    }

    public boolean ValidateToken(String access_Token, Date expirations){
        if(access_Token == null || access_Token.isEmpty())
            return false;

        Access_Token = access_Token;
        Expirations = expirations;
        return true;
    }
}
