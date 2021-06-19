package com.lsy.passcheck.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidateServiceImpl implements ValidateService {

    public static final String REGEX =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()+\\-])(?:([0-9a-zA-Z!@#$%^&*()+\\-])(?!.*\\1)){9,}$";

    @Override
    public boolean isValid(final String password) {
        return Pattern.matches(
                REGEX,
                password);
    }

}
