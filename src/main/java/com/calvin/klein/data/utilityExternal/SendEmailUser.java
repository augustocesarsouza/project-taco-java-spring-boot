package com.calvin.klein.data.utilityExternal;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.calvin.klein.data.utilityExternal.Interface.ICacheRedisUti;
import com.calvin.klein.data.utilityExternal.Interface.ISendEmailBrevo;
import com.calvin.klein.data.utilityExternal.Interface.ISendEmailUser;
import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmailUser implements ISendEmailUser {
    @Value("${JWT-SECRET-KEY}")
    private String secretKey;
    private final ISendEmailBrevo sendEmailBrevo;
    private final ICacheRedisUti cacheRedisUti;

    @Autowired
    public SendEmailUser(ISendEmailBrevo sendEmailBrevo, ICacheRedisUti cacheRedisUti) {
      this.sendEmailBrevo = sendEmailBrevo;
      this.cacheRedisUti = cacheRedisUti;
    }
    @Override
    public InfoErrors<String> sendEmail(User user) {
      try {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        LocalDateTime currentUtcDateTime = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime expires = currentUtcDateTime.plusMinutes(10);
        Date expiresDate = Date.from(expires.toInstant(ZoneOffset.UTC));

        String token = JWT.create()
                .withClaim("id", user.getId().toString())
                .withExpiresAt(expiresDate)
                .sign(algorithm);

        String key = "TokenString" + user.getId().toString();
        String cache = cacheRedisUti.getString(key);

        if(cache == null){
           cacheRedisUti.setString(key, token, 10, TimeUnit.MINUTES);
        }

        String url = "http://localhost:5700/minha-conta/confirmacao-de-email?token="+token;
        var resultSend = sendEmailBrevo.sendEmail(user, url);

        if(!resultSend.IsSuccess)
          return InfoErrors.Fail(resultSend.Message);

        return InfoErrors.Ok("tudo certo com o envio do email");
      }catch (Exception ex){
        return InfoErrors.Fail("Erro no envio do email, ERROR: " + ex.getMessage());
      }
    }
    @Override
    public InfoErrors<String> sendEmailConfirmRegisterUser(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            LocalDateTime currentUtcDateTime = LocalDateTime.now(ZoneOffset.UTC);
            LocalDateTime expires = currentUtcDateTime.plusMinutes(10);
            Date expiresDate = Date.from(expires.toInstant(ZoneOffset.UTC));

            String token = JWT.create()
                    .withClaim("id", user.getId().toString())
                    .withExpiresAt(expiresDate)
                    .sign(algorithm);

            String key = "TokenString" + user.getId().toString();
            String cache = cacheRedisUti.getString(key);

            if(cache == null){
                cacheRedisUti.setString(key, token, 10, TimeUnit.MINUTES);
            }

            String url = "http://localhost:4200/my-account/confirm-email/"+token;
            var resultSend = sendEmailBrevo.sendEmail(user, url);

            if(!resultSend.IsSuccess)
                return InfoErrors.Fail(resultSend.Message);

            return InfoErrors.Ok("tudo certo com o envio do email");
        }catch (Exception ex){
            return InfoErrors.Fail("Erro no envio do email, ERROR: " + ex.getMessage());
        }
    }

    @Override
    public InfoErrors<String> sendTokenForEmailChangePassword(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            LocalDateTime currentUtcDateTime = LocalDateTime.now(ZoneOffset.UTC);
            LocalDateTime expires = currentUtcDateTime.plusMinutes(10);
            Date expiresDate = Date.from(expires.toInstant(ZoneOffset.UTC));

            String token = JWT.create()
                    .withClaim("id", user.getId().toString())
                    .withExpiresAt(expiresDate)
                    .sign(algorithm);

            String key = "TokenString" + user.getId().toString();
            String cache = cacheRedisUti.getString(key);

            if(cache == null){
                cacheRedisUti.setString(key, token, 10, TimeUnit.MINUTES);
            }

            String url = "http://localhost:4200/my-account/password-change/"+token;
            var resultSend = sendEmailBrevo.sendEmail(user, url);

            if(!resultSend.IsSuccess)
                return InfoErrors.Fail(resultSend.Message);

            return InfoErrors.Ok("tudo certo com o envio do email");
        }catch (Exception ex){
            return InfoErrors.Fail("Erro no envio do email, ERROR: " + ex.getMessage());
        }
    }

    @Override
    public InfoErrors<String> sendCodeRandom(User user, int code) {
        return  sendEmailBrevo.sendCode(user, code);
    }

    @Override
    public InfoErrors<String> sendCodeRandomString(int codeI, String email) {
        return  sendEmailBrevo.sendCodeString(codeI, email);
    }
}
