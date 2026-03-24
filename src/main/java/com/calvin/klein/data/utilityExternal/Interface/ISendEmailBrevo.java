package com.calvin.klein.data.utilityExternal.Interface;

import com.calvin.klein.domain.InfoErrors.InfoErrors;
import com.calvin.klein.domain.entities.User;

public interface    ISendEmailBrevo {
    InfoErrors<String> sendEmail(User user, String url);
    InfoErrors<String> sendCode(User user, int codeRandom);
    InfoErrors<String> sendCodeString(int codeI, String email);
}
