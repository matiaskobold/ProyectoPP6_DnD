package com.matiaskobold.proyectopp6;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matiaskobold.proyectopp6.model.Clan;
import com.matiaskobold.proyectopp6.service.ClanService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

    @Test
    @Order(1)
    public void createClanTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Clan clan1 = new Clan("The stonemasons", "Clan of stonemasons from Karak Peaks", "Dwarfish", "Karak Peaks", "Royal Stonemasons");
        //Clan clan2 = new Clan("The royals", "Clan of smithys from Karak Kadrin", "Dwarfish", "Karak Kadrin", "Royal Smithys");
        clanService.createClan(clan1);
        assertThat(restTemplate.getForObject("http://localhost:"+port+"/clans", String.class))
                .isEqualTo("["+objectMapper.writeValueAsString(clan1)+"]");
        System.out.println("asd");
    }
}
