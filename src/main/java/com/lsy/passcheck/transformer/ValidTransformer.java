package com.lsy.passcheck.transformer;

import com.lsy.passcheck.dto.ValidDto;

public interface ValidTransformer {

    ValidDto toValidDto(boolean valid);

}
