package com.yuriy.labs.springcloud.microservices.bill.security;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableResourceServer
public class ResourceServerConfig {//extends ResourceServerConfigurerAdapter {

/*    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId("Bill");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/bill/**").access("hasAuthority('ADMIN_WRITE')")
            .anyRequest().fullyAuthenticated();
    }*/
}
