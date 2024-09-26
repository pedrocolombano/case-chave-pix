package br.com.itau.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**")
                                                .filters(f -> f.prefixPath("/user-service"))
                                                .uri("lb://user-ms"))
                .route("account-service", r -> r.path("/accounts/**")
                                                  .filters(f -> f.prefixPath("/account-service"))
                                                  .uri("lb://account-ms"))
                .build();
    }

}
