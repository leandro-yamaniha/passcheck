package com.lsy.passcheck.transformer;

import com.lsy.passcheck.dto.ValidDto;
import org.springframework.stereotype.Component;

@Component
public class ValidTransformerImpl implements ValidTransformer {

    @Override
    public ValidDto toValidDto(final boolean valid) {

        return new ValidDto(valid);

    }

}
