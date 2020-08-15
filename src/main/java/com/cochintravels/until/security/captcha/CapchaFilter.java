package com.cochintravels.until.security.captcha;

import com.cochintravels.until.exception.CochinTravelsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CapchaFilter extends GenericFilterBean {

    @Autowired
    private ICaptchaService captchaService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getRequestURI().contains("/email/postContactForm")) {
            try {
                captchaService.processResponse(req.getHeader("recaptchaReactive"));
            } catch (CochinTravelsException e) {
                logger.error("Invalid Captcha");
                res.reset();
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            chain.doFilter(request, response);
        }
        ;
    }
}