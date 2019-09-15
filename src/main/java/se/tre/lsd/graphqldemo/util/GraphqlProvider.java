package se.tre.lsd.graphqldemo.util;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.core.io.ClassPathResource;
import se.tre.lsd.graphqldemo.api.datafetch.BallFetcher;
import se.tre.lsd.graphqldemo.api.datafetch.HelloFetcher;
import se.tre.lsd.graphqldemo.api.datafetch.StuffFetcher;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class GraphqlProvider {

    public static GraphQL graphQL() {

        String schema = getFileAsString();
        GraphQLSchema graphQLSchema = buildSchema(schema);
        return GraphQL.newGraphQL(graphQLSchema).build();

    }

    private static GraphQLSchema buildSchema(String schema) {

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schema);
        RuntimeWiring runtimeWiring = buildWiring();

        return new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private static RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("hello", HelloFetcher.getHelloWorldDataFetcher())
                        .dataFetcher("echo", HelloFetcher.getEchoDataFetcher())
                        .dataFetcher("getStuff", StuffFetcher.getStuff())
                        .build())
                .type(newTypeWiring("Stuff")
                        .dataFetcher("ball", BallFetcher.getBall())
                        .build())
                .build();

    }

    private static String getFileAsString() {
        try {
            File file = new ClassPathResource("schema.graphqls").getFile();
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get/read file");
        }
    }
}
