package com.talk.talk.config.filter;

import com.talk.talk.config.jwt.GenerateJwt;
import com.talk.talk.config.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private GenerateJwt generateJwt;

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new JwtFilter(generateJwt, jwtUtils));
        return filterRegistrationBean;
    }
}