package com.carbigdata.ms.controller.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.carbigdata.ms.controller.auth.dto.LoginClientRequestDTO;
import com.carbigdata.ms.controller.auth.dto.RegisterClientRequestDTO;
import com.carbigdata.ms.utils.TestUtils;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc 
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should be able to register a new client")
    public void createNewClient() throws Exception {
        RegisterClientRequestDTO dto = new RegisterClientRequestDTO("name", "password", "00000000001", LocalDateTime.of(2000, 07, 24, 12, 12, 10));
        
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Should not be able to register a new client with cpf already created")
    public void createNewClientWithConflitc() throws Exception {
        RegisterClientRequestDTO dto = new RegisterClientRequestDTO("name", "password", "00000000001", LocalDateTime.of(2000, 07, 24, 12, 12, 10));
        
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    @DisplayName("Should be able to login with cpf and password")
    public void loginRequest() throws Exception {
        LoginClientRequestDTO dto = new LoginClientRequestDTO("00000000001", "password");
        
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Should not able to make login with cpf and password incorrect")
    public void loginRequestWithError() throws Exception {
        LoginClientRequestDTO dto = new LoginClientRequestDTO("00000000001", "error_password");
        
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.object2Json(dto)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
