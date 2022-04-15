package com.maza.divisorsmapper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maza.divisorsmapper.dto.ErrorCode;
import com.maza.divisorsmapper.dto.ErrorResponse;
import com.maza.divisorsmapper.dto.MapNumbersRequest;
import com.maza.divisorsmapper.dto.MapNumbersResponse;
import com.maza.divisorsmapper.service.DivisorsService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
public class DivisorsControllerTest {

    private static final String DIVISORS_URL = "/divisors/animals/";
    private static final String INVALID_CATEGORY_URL = "/divisors/test/";
    private static final String EXAMPLE_CATEGORY = "animals";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DivisorsService service;

    @Test
    public void givenGetDivisors_whenCorrectInput_thenSuccess() throws Exception {
        final List<Integer> numbers = List.of(1, 2, 3);
        given(service.mapNumbers(EXAMPLE_CATEGORY, numbers)).willReturn(MapNumbersResponse.builder().build());
        this.mockMvc.perform(MockMvcRequestBuilders.get(DIVISORS_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prepareRequest(numbers)))
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void givenGetDivisors_whenInvalidInputBody_thenBadRequest() throws Exception {
        final List<Integer> numbers = List.of(-11, 2, 3);
        given(service.mapNumbers(EXAMPLE_CATEGORY, numbers)).willReturn(MapNumbersResponse.builder().build());
        final var response = ErrorResponse.builder()
            .code(ErrorCode.INVALID_REQUEST)
            .message("Number should not be less than 1")
            .build();

        this.mockMvc.perform(MockMvcRequestBuilders.get(DIVISORS_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prepareRequest(numbers)))
            )
            .andExpect(status().isBadRequest())
            .andExpect(content().json(objectMapper.writeValueAsString(response)))
            .andDo(print());
    }

    @Test
    public void givenGetDivisors_whenNoInputBody_thenInternalServerError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(DIVISORS_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isInternalServerError())
            .andExpect(MockMvcResultMatchers.jsonPath("$.code", Is.is("INTERNAL_SERVER_ERROR")))
            .andDo(print());
    }

    @Test
    public void givenGetDivisors_whenInvalidCategory_thenBadRequestError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(INVALID_CATEGORY_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prepareRequest(List.of(1, 2, 3))))
            )
            .andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Invalid mapping category test")))
            .andDo(print());
    }

    private MapNumbersRequest prepareRequest(final List<Integer> numbers) {
        return new MapNumbersRequest(numbers);
    }

}
