package com.cochintravels.until.service.captcha;

import com.cochintravels.until.exception.CochinTravelsException;

public interface ICaptchaService {

    default void processResponse(final String response) throws CochinTravelsException {}

    default void processResponse(final String response, String action) throws CochinTravelsException {}

    String getReCaptchaSite();

    String getReCaptchaSecret();
}
