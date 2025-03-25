package com.messaginghub.be.gateway.configuration;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import com.messaginghub.be.gateway.filter.BeforeFilterFunctions;

@Configuration
public class MessagingRouteConfiguration {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Value("${uris.be-messaging}")
    private String messagingRouteUri;

    @Bean
    RouterFunction<ServerResponse> messagingRoute() {
        return route("be-messaging")
                .route(path("/messaging-hub/messaging/v1/**"), http(messagingRouteUri))
                .before(request -> {
                    String authorization = request.headers().firstHeader("Authorization");
                    String token = authorization.split(" ")[1];
                    Jwt decoded = jwtDecoder.decode(token);
                    String userId = decoded.getClaim("id");
                    return BeforeFilterFunctions.loggerUserId(userId).apply(request);
                }).build();
    }

}
