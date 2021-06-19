package com.lsy.passcheck.integration.transformer;

import com.lsy.passcheck.transformer.ValidTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValidateTransformerTest {

    @Autowired
    private ValidTransformer transformer;

    @Test
    void whenTrue(){

        var dto = transformer.toValidDto(true);
        assertNotNull(dto);
        assertTrue(dto.isValid());

    }

    @Test
    void whenFalse(){

        var dto = transformer.toValidDto(false);
        assertNotNull(dto);
        assertFalse(dto.isValid());

    }

}
