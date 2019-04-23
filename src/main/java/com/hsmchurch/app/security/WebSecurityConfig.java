package com.hsmchurch.app.security;

import com.hsmchurch.app.security.jwt.HeaderTokenExtractor;
import com.hsmchurch.app.security.jwt.JwtAuthenticationFilter;
import com.hsmchurch.app.security.jwt.JwtAuthenticationProvider;
import com.hsmchurch.app.security.jwt.JwtAuthenticationSuccessHandler;
import com.hsmchurch.app.security.oauth.SocialLoginAuthenticationProvider;
import com.hsmchurch.app.security.oauth.SocialLoginFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration(value = "authSecurityConfiguration")
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;

    @Autowired
    private JwtAuthenticationSuccessHandler successHandler;

    @Autowired
    private SocialLoginAuthenticationProvider socialProvider;

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private HeaderTokenExtractor headerTokenExtractor;

    private JwtAuthenticationFilter jwtFilter() throws Exception {
        log.info("json web token filter start");
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/api/v1/**");
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(matcher, headerTokenExtractor);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    private SocialLoginFilter socialFilter() throws Exception {
        SocialLoginFilter filter = new SocialLoginFilter("/socialLogin", successHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2 console이 csrf 설정이 안먹히기 때문에 security 설정을 배포 환경에 따라 분리
        selectProfile(http)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(socialFilter(), JwtAuthenticationFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().authenticated();
    }

    private HttpSecurity selectProfile(HttpSecurity http) throws Exception {
        if (isLocalMode()) return setLocalMode(http);
        else return setRealMode(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(this.socialProvider)
                .authenticationProvider(this.jwtProvider);
    }

    private boolean isLocalMode() {
        String profile = env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "local";
        return profile.equals("local");
    }

    private HttpSecurity setLocalMode(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin();
        http
                .authorizeRequests()
                .antMatchers("/", "/swagger**", "/swagger-resources/**/**", "/v2/api-docs", "/webjars/**", "/springfox**", "/me", "/h2-console/**", "/js/**", "/css/**", "/image/**", "/fonts/**", "/favicon**").permitAll()
                .and().csrf().disable();

        return http;
    }

    private HttpSecurity setRealMode(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin();
        http
                .authorizeRequests()
                .antMatchers("/batch/youtube", "/", "/swagger**", "/swagger-resources/**/**", "/v2/api-docs", "/webjars/**", "/springfox**", "/me", "/js/**", "/css/**", "/image/**", "/fonts/**", "/favicon**").permitAll()
                .and().csrf().disable();

        return http;
    }


}