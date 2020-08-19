package com.cochintravels.until.api;

import com.cochintravels.until.model.SheetJson;
import com.cochintravels.until.service.SheetRepository;
import com.cochintravels.until.service.SheetToJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SheetApi.class)
class SheetApiTest {

    @MockBean
    SheetRepository repo;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();
    private SheetJson sheetJson;

    @Test
    void testGetSheetByID() throws Exception {
        when(repo.findById(sheetJson.getSheetName())).thenReturn(Optional.of(sheetJson));
        mockMvc.perform(get("/sheets/getSheetByID/Sheet1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetRefreshDBStatus() throws Exception {
        for (int i = 1; i <= 3; i++) {
            mockServer.expect(ExpectedCount.once(),
                    requestTo(new URI("https://testapi.com/Sheet" + i)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(sheetJson))
                    );
        }
        mockMvc.perform(get("/sheets/getRefreshDBStatus")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        sheetJson = new SheetJson();
        sheetJson.setSheetName("Sheet1");
        sheetJson.setContent("[{\"name\":\"Spring\",\"description\":\"10Steps\"}]");
    }

    @AfterEach
    void tearDown() {
    }

    @TestConfiguration
    static class EmailApiTestContextConfiguration {

        @Bean
        public SheetToJsonService sheetToJsonService() {
            return new SheetToJsonService();
        }

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }

    }
}