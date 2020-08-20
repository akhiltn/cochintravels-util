package com.cochintravels.until.captcha;

import com.cochintravels.until.exception.CochinTravelsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CapchaFilter implements Filter {

    private ICaptchaService captchaService;

    public CapchaFilter(ICaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Override
    public void doFilter(ServletRequest serveleRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) serveleRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if(!HttpMethod.OPTIONS.matches(httpServletRequest.getMethod())){
            String captchaHeaderVal = httpServletRequest.getHeader("recaptchaReactive");
            try {
                captchaService.processResponse(captchaHeaderVal);
            } catch (CochinTravelsException e) {
                log.error("Invalid Captcha");
                httpServletResponse.reset();
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            log.info("Success Captcha");
        }
        chain.doFilter(serveleRequest, servletResponse);
    }

    @Bean
    public ICaptchaService bookingFormCaptcha() {
        return new CaptchaService();
    }
}
