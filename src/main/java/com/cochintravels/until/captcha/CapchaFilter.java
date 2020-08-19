/*
package com.cochintravels.until.security.captcha;

import com.cochintravels.until.exception.CochinTravelsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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
@Order(1)
public class CapchaFilter extends GenericFilterBean {

    @Autowired
    private ICaptchaService captchaService;

    @Override
    public void doFilter(ServletRequest serveleRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) serveleRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletRequest.getHeaderNames();
        httpServletRequest.getMethod();
        if(HttpMethod.POST.matches(httpServletRequest.getMethod())){
            System.out.println("Break");
        }
        if (httpServletRequest.getRequestURI().contains("/email/postContactForm") & HttpMethod.POST.matches(httpServletRequest.getMethod())) {
            String captchaHeaderVal = httpServletRequest.getHeader("recaptchaReactive");
            try {
                captchaService.processResponse(captchaHeaderVal);
            } catch (CochinTravelsException e) {
                logger.error("Invalid Captcha");
                httpServletResponse.reset();
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            logger.info("Success Captcha");
            chain.doFilter(serveleRequest, servletResponse);
        }
    }
}*/
