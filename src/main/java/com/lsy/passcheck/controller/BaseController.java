package com.lsy.passcheck.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<T> buildPostResponse(final boolean success, final T body) {

        final HttpStatus httpStatus = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(body);

    }

}
