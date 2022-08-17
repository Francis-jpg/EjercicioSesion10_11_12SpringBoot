package com.example.EjercicioSesion789SpringBoot.controller;

import com.example.EjercicioSesion789SpringBoot.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    //Creamos los atributos para los test

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;


    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobamos si puede encontrar a todos los laptop")
    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptop/", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptop = Arrays.asList(response.getBody());
        System.out.println(laptop.size());

    }

    @DisplayName("Comprobamos si puede encontrar un laptop desde su iDs")
    @Test
    void findOneById() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptop/1", Laptop[].class);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());



    }
    @DisplayName("Comprobamos si puede crear un laptop")
    @Test
    void createLaptop() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "brand": "Nuevo Laptop creado desde Spring test",
                    "model": "TRET",
                    "price": 3799.99
                }
                """;

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.POST , entity ,Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Nuevo Laptop creado desde Spring test", result.getBrand());
    }
}