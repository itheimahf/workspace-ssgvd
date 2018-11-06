package com.demo.service.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by gold.li on 2018/7/14.
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}