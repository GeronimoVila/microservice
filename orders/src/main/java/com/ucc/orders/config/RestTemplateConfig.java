package com.ucc.orders.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Value("${product.service.username}")
    private String productServiceUsername;

    @Value("${product.service.password}")
    private String productServicePassword;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(org.springframework.http.HttpRequest request, byte[] body,
                                                ClientHttpRequestExecution execution) throws IOException {
                String auth = productServiceUsername + ":" + productServicePassword;
                String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
                request.getHeaders().set(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth);
                return execution.execute(request, body);
            }
        }));

        return restTemplate;
    }
}