package com.lsy.passcheck.controller;

import com.lsy.passcheck.dto.PasswordDto;
import com.lsy.passcheck.dto.ValidDto;
import com.lsy.passcheck.service.ValidateService;
import com.lsy.passcheck.transformer.ValidTransformer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ValidateController.BASE_ENDPOINT)
public class ValidateController extends BaseController {

    public static final String BASE_ENDPOINT = "/validate";
    public static final String POST_VALIDATE_PASSWORD = "/password";
    private final ValidateService service;
    private final ValidTransformer transformer;

    ValidateController(final ValidateService service, final ValidTransformer transformer) {
        this.service = service;
        this.transformer = transformer;
    }

    @PostMapping(POST_VALIDATE_PASSWORD)
    public ResponseEntity<ValidDto> validatePassWord(@Valid @RequestBody final PasswordDto request) {

        return buildPostValidateResponse(
                service.isValid(request.getPassword()));

    }

    private ResponseEntity<ValidDto> buildPostValidateResponse(final boolean valid) {

        return buildPostResponse(valid, transformer.toValidDto(valid));

    }

}
