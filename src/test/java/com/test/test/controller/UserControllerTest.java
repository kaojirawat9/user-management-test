package com.test.test.controller;

import com.test.test.Controller.UserController;
import com.test.test.Model.User;
import com.test.test.Service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @Test
    void getUserTest() throws Exception {
        User user = new User(
                1L,
                "๋Jirawat Komngam",
                "jirawatku",
                "jirawatku@gmail.com",
                "08X-XXXX-XXXX",
                "jirawat.com");

        when(service.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("jirawatku"));
    }
}