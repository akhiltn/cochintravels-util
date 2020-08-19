package com.cochintravels.until.utility;

import com.cochintravels.until.captcha.CapchaFilter;
import com.cochintravels.until.captcha.CaptchaService;
import com.cochintravels.until.captcha.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class WebConfig {

    @Autowired
    ICaptchaService contactFormFilterService;

    @Bean
    public FilterRegistrationBean<CapchaFilter> contactFormFilterReg() {
        FilterRegistrationBean<CapchaFilter> filterRegBean = new FilterRegistrationBean<>();
        filterRegBean.setFilter(new CapchaFilter(contactFormFilterService));
        filterRegBean.addUrlPatterns("/email/*");
        filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        return filterRegBean;
    }

    @Bean
    public ICaptchaService contactFormFilterService() {
        return new CaptchaService();
    }


}
