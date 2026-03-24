package com.calvin.klein.data.utilityExternal.Interface;

import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.entities.User;

public interface ISendEmailUser {
    InfoErrors<String> sendEmail(User user);
    InfoErrors<String> sendEmailConfirmRegisterUser(User user);
    InfoErrors<String> sendTokenForEmailChangePassword(User user);
    InfoErrors<String> sendCodeRandom(User user, int code);
    InfoErrors<String> sendCodeRandomString(int codeI, String email);
}
