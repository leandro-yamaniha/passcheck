package com.lsy.passcheck.integration.service;

import com.lsy.passcheck.service.ValidateService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValidateServiceTest {

    @Autowired
    private ValidateService service;

    @ParameterizedTest(name =  "{0} is valid")
    @ValueSource(strings = { "AbTp9!fok", "AbTp9!fok2" })
    void whenPassIsValid(String pass){

        assertTrue(service.isValid(pass));

    }

    @ParameterizedTest(name =  "{0} is not valid")
    @ValueSource(strings = { "", "aa","ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA" ,"AbTp9 fok"})
    void whenPassIsNotValid(String pass){

        assertFalse(service.isValid(pass));

    }


}
