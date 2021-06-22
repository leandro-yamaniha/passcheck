package com.lsy.passcheck.integration.controller;

import com.lsy.passcheck.controller.ApiControllerAdvice;
import com.lsy.passcheck.controller.ValidateController;
import com.lsy.passcheck.dto.PasswordDto;
import com.lsy.passcheck.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ValidateControllerTest {

    private final String POST_VALIDATE_PASSWORD_ENDPOINT
            = ValidateController.BASE_ENDPOINT
                    .concat(ValidateController.POST_VALIDATE_PASSWORD);

    @Autowired
    private ValidateController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();
    }

    @ParameterizedTest(name =  "{0} is valid")
    @ValueSource(strings = { "AbTp9!fok", "AbTp9!fok2" })
    void okWhenPassWordIsValid(String password) throws Exception {

        postValidatePassword(password)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").exists())
                .andExpect(jsonPath("$.valid").value("true"));

    }

    @ParameterizedTest(name =  "{0} is not valid")
    @ValueSource(strings = { "", "aa","ab", "AAAbbbCc", "AbTp9!foo", "AbTp9!foA" ,"AbTp9 fok"})
    void okWhenPassWordIsInvalid(String password) throws Exception {

        postValidatePassword(password)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valid").exists())
                .andExpect(jsonPath("$.valid").value("false"));

    }

    @Test
    void badRequestWhenMalformedRequestBody() throws Exception {

        postValidatePasswordPayload("invalid")
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.message").value("Malformed request body"));

    }

    private ResultActions postValidatePassword(String password) throws Exception {

        return postValidatePasswordPayload(JsonUtils.toString(new PasswordDto(password)));

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
