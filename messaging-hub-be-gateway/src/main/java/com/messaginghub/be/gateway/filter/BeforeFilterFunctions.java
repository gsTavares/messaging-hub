package com.messaginghub.be.gateway.filter;

import java.util.function.Function;

import org.springframework.web.servlet.function.ServerRequest;

public class BeforeFilterFunctions {
    public static Function<ServerRequest, ServerRequest> loggerUserId(String userId) {
        return request -> ServerRequest.from(request).header("X-User-Id", userId).build();
    } 
}
