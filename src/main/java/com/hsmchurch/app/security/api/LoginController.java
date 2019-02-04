package com.hsmchurch.app.security.api;

import com.hsmchurch.app.security.oauth.ServiceProviderInfo;
import com.hsmchurch.app.security.support.URLBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login(@RequestParam String provider) throws URISyntaxException {

        final ServiceProviderInfo serviceProviderInfo = ServiceProviderInfo.valueOf(provider);

        String aa = "https://kauth.kakao.com/oauth/authorize?"
                + "client_id=" + serviceProviderInfo.getClientId()
                + "&" + "redirect_uri=" + new URI(serviceProviderInfo.getRedirectUri()).toString()
                + "&" + "response_type=code";

        URI uri = new URI(aa);

        return "redirect:" + uri.toString();
    }

    private static UriComponentsBuilder getBuilder(final ServiceProviderInfo serviceProviderInfo) throws UnsupportedEncodingException {
        return UriComponentsBuilder.fromHttpUrl(serviceProviderInfo.getOAuthHost())
                .queryParam("client_id", URLEncoder.encode(serviceProviderInfo.getClientId(), "UTF-8"))
                .queryParam("redirect_uri", URLEncoder.encode(serviceProviderInfo.getRedirectUri(), "UTF-8"))
                .queryParam("response_type", URLEncoder.encode(serviceProviderInfo.getResponseType(), "UTF-8"));
    }

}
