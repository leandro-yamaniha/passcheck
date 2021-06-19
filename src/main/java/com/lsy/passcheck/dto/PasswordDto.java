package com.lsy.passcheck.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Valid
public class PasswordDto {

    @NotEmpty
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
