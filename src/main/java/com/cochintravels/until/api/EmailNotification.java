package com.cochintravels.until.api;

import com.cochintravels.until.exception.CochinTravelsException;
import com.cochintravels.until.model.BookingForm;
import com.cochintravels.until.service.EmailService;
import com.cochintravels.until.service.captcha.ICaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("email")
public class EmailNotification {

    @Autowired
    private ICaptchaService captchaService;

    @Autowired
    private EmailService service;

    @Value("${cochintravels.mail.setTo}")
    private String[] recipient;

    @Value("${cochintravels.mail.subject}")
    private String subject;

    @PostMapping("/postContactForm")
    public ResponseEntity<String> postContactFormEmail(@RequestBody BookingForm message) {
        try {
            captchaService.processResponse(message.getRecaptchaReactive());
        } catch (CochinTravelsException e) {
            log.error("reCaptcha Exception");
            e.printStackTrace();
            return new ResponseEntity<>("Error!! "+e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Something went wrong");
            log.error(message.toString());
            return new ResponseEntity<>("Server Error!! Sorry for Inconvenience", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.sendMail(recipient, subject, message.toString());
        return new ResponseEntity<>("We will get back to you", HttpStatus.OK);
    }

    @GetMapping("/testMail")
    public HttpStatus getTestMail() {
        Assert.notNull(recipient,"Recipient of email is null");
        service.sendMail(recipient, "Hello", "Hello");
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
