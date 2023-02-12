package com.matiaskobold.proyectopp6;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.service.ClanService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClanServiceTest {

    @Autowired
    ClanService clanService;

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void tearDown() {
        clanService.deleteAll();
    }

    @Test
    @Order(1)
    public void createSingleClanTest_VALID() throws JSONException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(); //Tiene metodos para psar de Json A POJO y viceversa
        HttpHeaders headers = new HttpHeaders();        //Nos deja poner headers para craftear la request
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject clanJsonObject = new JSONObject();   //Me deja hacer un json Object con pares "key":"valor"
        clanJsonObject.put("name", "los locos")
                .put("description", "los mas locos")
                .put("main_language", "espaniol")
                .put("operations_base", "argentina")
                .put("title", "los mas mas locos");
        HttpEntity<String> request = new HttpEntity<>(clanJsonObject.toString(), headers);  //HttpEntity consiste de headers, body y http status, para una request o response
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:"+port+"/clans",request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        JsonNode root = objectMapper.readTree(response.getBody());
        /*The ObjectMapper.readTree() method deserializes JSON and builds a tree of JsonNode instances
        It takes a JSON source as input and returns the root node of the tree model created.
        Subsequently, we can use the root node to traverse the entire JSON tree
        */
        assertThat(root.path("name").asText()).isEqualTo("los locos");
        assertThat(root.path("description").asText()).isEqualTo("los mas locos");
        assertThat(root.path("main_language").asText()).isEqualTo("espaniol");
        assertThat(root.path("operations_base").asText()).isEqualTo("argentina");
        assertThat(root.path("title").asText()).isEqualTo("los mas mas locos");

    }
    @Test
    @Order(2)
    public void getSingleClanTest_VALID() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Clan clan1 = new Clan("The stonemasons", "Clan of stonemasons from Karak Peaks", "Dwarfish", "Karak Peaks", "Royal Stonemasons");
        clanService.createClan(clan1);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+"/clans", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("["+objectMapper.writeValueAsString(clan1)+"]");

    }
}
