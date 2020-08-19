package com.cochintravels.until.api;

import com.cochintravels.until.model.BookingForm;
import com.cochintravels.until.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailApi.class)
class EmailApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender emailSender;
    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testPostContactFormEmail() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        BookingForm bookingForm = new BookingForm();
        bookingForm.setFirstName("TestFistName");
        bookingForm.setLastName("TestLastName");
        bookingForm.setEmail("test@mail.com");
        bookingForm.setMobile("1234");
        bookingForm.setStartDate("123");
        bookingForm.setEndDate("123");
        bookingForm.setAdditionalDetails("qwerty");
        bookingForm.setRecaptchaReactive("qwerty");

        String json = mapper.writeValueAsString(bookingForm);

        mockMvc.perform(
                post("/email/postContactForm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @TestConfiguration
    static class EmailApiTestContextConfiguration {

        @Bean
        public EmailService emailService() {
            return new EmailService();
        }
    }
}