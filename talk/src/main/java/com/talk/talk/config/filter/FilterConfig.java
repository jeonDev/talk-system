package com.talk.talk.config.filter;

import com.talk.talk.config.jwt.GenerateJwt;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private GenerateJwt generateJwt;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<JwtFilter>(new JwtFilter(generateJwt));
        filterRegistrationBean.addUrlPatterns("/user/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistrationBean;
    }
}
