package com.lsy.passcheck.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
public class PasswordDto {

    @NotNull
    private String password;

    public PasswordDto() {
    }

    public PasswordDto(final String password) {
        this.password = password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
