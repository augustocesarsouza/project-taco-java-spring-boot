package com.calvin.klein.application.util.interfaces;

public interface IBCryptPasswordEncoderUtil {
    String encodePassword(String password);
    boolean matches(String rawPassword, String encodedPassword);
}

