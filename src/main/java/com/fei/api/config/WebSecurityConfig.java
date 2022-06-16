package com.fei.api.config;

import com.fei.api.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
                .antMatchers("/api/v1/user/signup").permitAll()
                .antMatchers("/api/v1/user/login").permitAll()
                .antMatchers("/api/v1/user/list").permitAll()
                .antMatchers("/api/v1/category").permitAll()
                .antMatchers("/api/v1/post/list").permitAll()
                .antMatchers("/api/v1/post/list/*").permitAll()
                .antMatchers("/api/v1/recomment/*").permitAll()
                .antMatchers("/api/v1/comment/*").permitAll()
                .antMatchers("/api/v1/post/category/*").permitAll()
                .antMatchers("/api/v1/post/*").permitAll()
                .antMatchers("/api/v1/blog/*").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/console").permitAll()
            .antMatchers("/console/").permitAll()
            .antMatchers("/console/*").permitAll()
            .antMatchers("/authenticate").permitAll()
            .anyRequest().authenticated();

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
    }
}