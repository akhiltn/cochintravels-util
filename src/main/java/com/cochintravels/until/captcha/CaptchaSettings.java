package com.cochintravels.until.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
public class CaptchaSettings {

    private String site;
    private String secret;

}
