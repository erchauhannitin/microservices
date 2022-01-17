package com.perfect.microservices.payment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired UserDetailsService userDetailsService;
    @Autowired DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.userDetailsService(userDetailsService);
//        auth.jdbcAuthentication().dataSource(dataSource);

        auth.inMemoryAuthentication()
                .withUser("perfect")
                .password("perfect")
                .roles("USER")
                .and()
                .withUser("ms")
                .password("ms")
                .roles("ADMIN");
        
//        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/swagger-ui").permitAll()
                .antMatchers("/payment").hasRole("ADMIN")
                .antMatchers("/api").hasAnyRole("USER", "ADMIN")
                .and().formLogin();
    }
}
