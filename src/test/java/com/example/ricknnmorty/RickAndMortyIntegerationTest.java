package com.example.ricknnmorty;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RickAndMortyIntegerationTest {

    @Autowired
    MockMvc mockMvc;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void beforeAll() throws IOException {
        mockWebServer=new MockWebServer();
        mockWebServer.start();
    }
    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry){
        registry.add("rickety.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    public void test() throws Exception {

        mockWebServer.enqueue(new MockResponse()
                        .setHeader("Content-Type","application/json")
                .setBody("""
                            {
                            "results":[
                                {
                                    "id": 1,
                                    "name": "Rick Sanchez",
                                    "species": "Human"
                                }
                            ]
                            }
                        """));
        mockMvc.perform(get("/api/RickMorty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Rick Sanchez"));

    }

    @AfterAll
    static void afterAll() throws IOException {
        mockWebServer.shutdown();
    }

}
