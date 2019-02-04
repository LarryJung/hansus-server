package com.hsmchurch.app.security.config;

import com.hsmchurch.app.security.filter.SocialLoginFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // h2 console이 csrf 설정이 안먹히기 때문에 security 설정을 배포 환경에 따라 분리
        if (isLocalMode()) {
            setLocalMode(http);
        } else {
            setRealMode(http);
        }
    }

    private boolean isLocalMode() {
        String profile = env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "local";
        return profile.equals("local");
    }

    private void setLocalMode(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and().csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/me", "/h2-console/**", "/login/**", "/js/**", "/css/**", "/image/**", "/fonts/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and();

    }

    private void setRealMode(HttpSecurity http) throws Exception {
    }


}