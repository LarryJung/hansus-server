package com.hsmchurch.app.security.filter;

import com.hsmchurch.app.security.oauth.ServiceProviderInfo;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SocialLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        final String path = httpServletRequest.getRequestURI();

        if (path.equals("/login")) {
            final String serviceProvider = request.getParameter("provider");
            final ServiceProviderInfo serviceProviderInfo = ServiceProviderInfo.valueOf(serviceProvider);
            final UriComponentsBuilder builder = getBuilder(serviceProviderInfo);
            System.out.println(builder.toUriString());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
            System.out.println(result);
        }

    }

    private static UriComponentsBuilder getBuilder(final ServiceProviderInfo serviceProviderInfo) {
        return UriComponentsBuilder.fromHttpUrl(serviceProviderInfo.getOAuthHost())
                .queryParam("client_id", serviceProviderInfo.getClientId())
                .queryParam("redirect_uri", serviceProviderInfo.getRedirectUri())
                .queryParam("response_type", serviceProviderInfo.getResponseType());
    }
}
