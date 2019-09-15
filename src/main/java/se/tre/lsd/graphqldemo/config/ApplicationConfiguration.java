package se.tre.lsd.graphqldemo.config;

import graphql.GraphQL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.tre.lsd.graphqldemo.util.GraphqlProvider;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GraphQL graphQL() {
        return GraphqlProvider.graphQL();
    }

}
