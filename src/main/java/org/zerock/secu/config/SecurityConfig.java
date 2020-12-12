package org.zerock.secu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.secu.service.SecuCheckService;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public UserDetailsService userDetailsService(){
        return new SecuCheckService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService());
        // log.info("security configure........");
        // auth.inMemoryAuthentication().withUser("M90").password("$2a$10$z6mTEnBvL4NVH6jupxTl8eL/ZNEf.Bd2I4JY14/mAo4N7Z7yQqQhW")
        // .roles("MEMBER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("configure....................");
        log.info("configure....................");

        http.authorizeRequests().antMatchers("/sample/all").permitAll();
        http.authorizeRequests().antMatchers("/sample/member").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/sample/admin").hasRole("ADMIN");

        http.formLogin();
        http.csrf().disable();
    }
    
    

}