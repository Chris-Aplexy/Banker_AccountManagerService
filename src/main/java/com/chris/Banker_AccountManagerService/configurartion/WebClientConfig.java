package com.chris.Banker_AccountManagerService.configurartion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webclientBuilder(){
        return WebClient.builder().build();
    }
}
