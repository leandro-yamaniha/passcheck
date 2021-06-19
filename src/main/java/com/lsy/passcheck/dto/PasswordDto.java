package com.lsy.passcheck.dto;

public class PasswordDto {

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
