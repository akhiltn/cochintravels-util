package com.cochintravels.until.api;

import com.cochintravels.until.model.BookingForm;
import com.cochintravels.until.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("email")
public class EmailApi {

    @Autowired
    private EmailService emailService;

    @Value("${cochintravels.mail.setTo}")
    private String[] recipient;

    @Value("${cochintravels.mail.subject}")
    private String subject;

    @PostMapping("/postContactForm")
    public ResponseEntity<String> postContactFormEmail(@RequestBody BookingForm message) {
        emailService.sendMail(subject, message.toString(), recipient);
        return new ResponseEntity<>("We will get back to you", HttpStatus.OK);
    }
}
