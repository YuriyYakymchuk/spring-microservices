package com.yuriy.labs.springcloud.microservices.guest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    private static final String GRANT_TYPE = "password";

    @Value("${auth.username}")
    private String username;

    @Value("${auth.password}")
    private String password;

    @Value("${auth.server.token.url}")
    private String tokenUrl;

    @Value("${auth.client.id}")
    private String clientID;

    @Value("${auth.client.secret}")
    private String clientSecret;

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = new ArrayList<String>();
        scopes.add("openid");

        resource.setAccessTokenUri(tokenUrl);

        resource.setClientId(clientID);
        resource.setClientSecret(clientSecret);
        resource.setGrantType(GRANT_TYPE);
        resource.setScope(scopes);

        resource.setUsername(username);
        resource.setPassword(password);

        return resource;
    }

    @Bean
    public OAuth2RestOperations restTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestOperations oAuth2RestTemplate = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
        return oAuth2RestTemplate;
    }
}
