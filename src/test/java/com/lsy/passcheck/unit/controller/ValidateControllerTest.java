package com.lsy.passcheck.unit.controller;

import com.lsy.passcheck.controller.ApiControllerAdvice;
import com.lsy.passcheck.controller.ValidateController;
import com.lsy.passcheck.utils.JsonUtils;
import com.lsy.passcheck.dto.PasswordDto;
import com.lsy.passcheck.dto.ValidDto;
import com.lsy.passcheck.service.ValidateService;
import com.lsy.passcheck.transformer.ValidTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class ValidateControllerTest {

    private final String POST_VALIDATE_PASSWORD_ENDPOINT
            = ValidateController.BASE_ENDPOINT
                    .concat(ValidateController.POST_VALIDATE_PASSWORD);

    private MockMvc mockMvc;

    @Mock
    private ValidateService service;

    @Mock
    private ValidTransformer transformer;

    @InjectMocks
    private ValidateController controller;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();
    }

    @Test
    void okWhenPassWordIsValid() throws Exception {

        PasswordDto request = new PasswordDto("test");
        mockValid(true);
        postValidatePassword(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").exists())
                .andExpect(jsonPath("$.valid").value("true"));

    }

    @Test
    void badRequestWhenPasswordIsInvalid() throws Exception{

        PasswordDto request = new PasswordDto("test");
        mockValid(false);
        postValidatePassword(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valid").exists())
                .andExpect(jsonPath("$.valid").value("false"));

    }

    @Test
    void badRequestWhenMalformedRequestBody() throws Exception {

        postValidatePasswordPayload("test")
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.message").value("Malformed request body"));

    }

    @Test
    void badRequestWhenPasswordIsNull() throws Exception {

        postValidatePassword(new PasswordDto())
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.password").exists());

    }

    @Test
    void badRequestWhenPasswordIsEmpty() throws Exception {

        mockValid(false);
        postValidatePassword(new PasswordDto(""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valid").exists())
                .andExpect(jsonPath("$.valid").value("false"));

    }

    private void mockValid(boolean valid) {

        when(service.isValid(anyString())).thenReturn(valid);
        when(transformer.toValidDto(valid))
                .thenReturn(new ValidDto(valid));

    }

    private ResultActions postValidatePassword(PasswordDto request) throws Exception {

        return postValidatePasswordPayload(JsonUtils.toString(request));

    }

    private ResultActions postValidatePasswordPayload(String request) throws Exception {

        return mockMvc.perform(
                post(POST_VALIDATE_PASSWORD_ENDPOINT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

    }

}
