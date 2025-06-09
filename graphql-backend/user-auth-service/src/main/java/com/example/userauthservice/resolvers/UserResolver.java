package com.example.userauthservice.resolvers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.stereotype.Component;

@DgsComponent
@Component
public class UserResolver {

    @DgsQuery
    public String hello() {
        return "Hello, world!";
    }
}
