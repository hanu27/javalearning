package com.javalearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@Configuration
//@EnableWebMvc
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicCustomizer
                = Customizer.withDefaults();
        HttpBasicConfigurer<HttpSecurity> configure = new HttpBasicConfigurer<>();
        httpBasicCustomizer.customize(configure);
        http.authorizeHttpRequests(authz -> {
            authz.requestMatchers("*/*").permitAll();
            authz.anyRequest().authenticated();
        })
                .formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/login")
                    .failureForwardUrl("/error");
        })
        ;
        return http.httpBasic(httpBasicCustomizer).build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }
}
