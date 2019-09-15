package se.tre.lsd.graphqldemo.api.controller;

import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class GraphqlController {

    private final GraphQL graphQL;

    public GraphqlController(final GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @PostMapping(value = "/graphql", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> graphql(@RequestBody Map<String, Object> body) {

        final String operationName = (String) body.get("operationName");
        final Optional<String> query = Optional.ofNullable((String) body.get("query"));
        final Optional<Map<String, Object>> variables = Optional.ofNullable((Map<String, Object>) body.get("variables"));

        return executeGraphqlQuery(query.orElse(""), operationName, variables.orElse(new LinkedHashMap<>()));

    }

    private Map<String, Object> executeGraphqlQuery(String query, String operationName, Map<String, Object> variables) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .operationName(operationName)
                .variables(variables)
                .build();
        return this.graphQL.execute(executionInput).toSpecification();
    }

}
