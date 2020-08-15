package com.cochintravels.until.api;

import com.cochintravels.until.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@RestController
public class EmailNotification {

    @Autowired
    private EmailService service;

    @PostMapping("/postNotificationEmail")
    public HttpStatus postNotificationEmail(String body){
        return HttpStatus.OK;
    }

    @GetMapping("/testMail")
    public HttpStatus getTestMail(){
        service.sendMail("noreply@gmail.com","Hello","Hello");
        return HttpStatus.OK;
    }


    @GetMapping("/lombok")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }
}
