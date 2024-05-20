package com.example.testtaskov;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
abstract class TestTaskOvApplicationTests {

    @Autowired
    protected MockMvc mockMvc;
    @PersistenceContext
    protected EntityManager entityManager;

    @Test
    void contextLoads() {
    }

}
