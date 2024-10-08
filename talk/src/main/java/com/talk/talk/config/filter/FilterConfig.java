package com.talk.talk.config.filter;

import com.talk.talk.config.jwt.GenerateJwt;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestFilter> requestFilter() {
        FilterRegistrationBean<RequestFilter> filterRegistrationBean = new FilterRegistrationBean<>(new RequestFilter());
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(GenerateJwt generateJwt) {
        FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>(new JwtFilter(generateJwt));
        filterRegistrationBean.addUrlPatterns("/user/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
